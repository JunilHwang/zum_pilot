package zuminternet.pilot.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import zuminternet.pilot.advice.exception.AuthException;
import zuminternet.pilot.domain.dao.entity.User;
import zuminternet.pilot.service.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String secretKey;
  private long tokenValidMS = 1000L * 60 * 60;
  private final UserService userService;

  /**
   * WAS가 띄워질 때 자동으로 실행
   * secretKey 초기화
   */
  @PostConstruct
  protected void init () {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  /**
   * 토큰 생성
   * @param userId : user의 id
   * @param roles : user의 역할. 현재는 ROLE_USER 만 존재
   * @return 토큰 값 반환
   */
  public String createToken(String userId, List<String> roles) {
      Claims claims = Jwts.claims().setSubject(userId); // claim 생성
      claims.put("roles", roles); // role 지정
      Date now = new Date();
      return Jwts.builder()
              .setClaims(claims) // claim 지정
              .setIssuedAt(now) // 토큰 발행 일자 지정
              .setExpiration(new Date(now.getTime() + tokenValidMS)) // 유효 시간 지정
              .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret 값 지정
              .compact(); // 위의 내용을 압축 후 반환
  }

  /**
   * Jwt 토큰으로 인증 정보를 조회
   * @param token : 검증할 token
   * @return Token에 대한 Authorization 정보를 생성 후 반환
   */
  public Authentication getAuthentication(String token) {
      // token으로 부터 user id 추출 후, id를 통해서 user 정보를 가져옴
      User user = userService.loadUserByUsername(getId(token));

      // user 정보로 부터 Authorization 생성
      return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
  }

  /**
   * JWT 에서 User ID 추출
   * @param token : jwt
   * @return user ID
   */
  public String getId(String token) {
      return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * Request의 Header에서 token 파싱 : "X-AUTH-TOKEN: jwt토큰"
   * @param req : request 정보
   * @return : request 에 포함된 jwt 반환
   */
  public String resolveToken(HttpServletRequest req) {
      return req.getHeader("X-AUTH-TOKEN");
  }

  /**
   * Jwt 토큰의 유효성(만료일자) 검증
   * @param token : jwt
   * @return 토큰 유효 시간의 만료 여부(true or false)
   */
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Authrizatino 검증하기
   * @return
   * @throws AuthException
   */
  public String authorization () throws AuthException {
    // Security Context 에 저장한 authentication 정보 가져오기
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // Token 에서 가져온 User Id가 익명의 사용자일 경우 예외처리
    String userId = auth.getName();
    if (userId.equals("anonymousUser")) {
       throw new AuthException();
    }
    return userId;
  }
}
