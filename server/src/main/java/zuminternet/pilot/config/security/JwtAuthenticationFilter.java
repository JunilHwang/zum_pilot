package zuminternet.pilot.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {
  private JwtTokenProvider jwtTokenProvider;

  /**
   * 생성자 설정
   * @param jwtTokenProvider : bean 주입
   */
  public JwtAuthenticationFilter (JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  /**
   * jwt로 filtering
   * @param request : http request
   * @param response : http response
   * @param chain : filtering request 목록
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    // request의 header에 포함된 Token 정보를 가져온다.
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

    // token 정보가 존재할 때만 token을 검증
    if (token != null && jwtTokenProvider.validateToken(token)) {
      // token에서 값을 추출하여
      Authentication auth = jwtTokenProvider.getAuthentication(token);
      // context에 저장한다.
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    chain.doFilter(request, response);
  }
}
