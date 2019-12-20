package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zuminternet.pilot.advice.exception.SignUpException;
import zuminternet.pilot.advice.exception.UserIdNotFoundException;
import zuminternet.pilot.advice.exception.UserNotFoundException;
import zuminternet.pilot.domain.dao.entity.User;
import zuminternet.pilot.domain.dao.entity.Video;
import zuminternet.pilot.domain.dao.repository.UserRepository;
import zuminternet.pilot.domain.dao.repository.VideoRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final VideoRepository videoRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * Parameter에서 id와 pw를 추출 fetch를 실행한다.
   * @param params : { id, pw }
   * @return id, pw와 일치하는 user 데이터를 가져옴
   */
  public User fetch (User params) {
    return fetch(params.getId(), params.getPw());
  }

  /**
   * 먼저 id를 기준으로 db에서 데이터를 가져온 후, pw 검사
   * @param id
   * @param pw
   * @return id, pw와 일치하는 user 데이터를 가져옴
   */
  public User fetch (String id, String pw) throws UserNotFoundException {
    User user = loadUserByUsername(id); // id를 기준으로 user 정보를 가져옴

    // 비밀번호가 일치하지 않으면 Exception 발생
    if (passwordEncoder.matches(pw, user.getPw())) {
      throw new UserNotFoundException();
    }
    return user;
  }

  /**
   * Parameter에서 id, pw, name을 추출하여 insert 실행
   * @param params : { id, pw, name }
   */
  public void insert (User params) {
    insert(params.getId(), params.getPw(), params.getName());
  }

  /**
   * DB에 일치하는 유저 정보가 없으면 유저 정보 삽입
   * @param id
   * @param pw
   * @param name
   * @throws SignUpException : 회원 가입 실패 처리
   */
  public void insert (String id, String pw, String name) throws SignUpException {
    // 비밀번호 암호화
    String encodedPw = passwordEncoder.encode(pw);

    // 이미 가입 된 회원이라면 Exception을 통해 Fail response 반환
    if (get(id) != null) throw new SignUpException();

    // 가입 된 회원이 아니라면 DB에 등록
    userRepository.save(
      User.builder()
        .id(id)
        .pw(encodedPw)
        .name(name)
        .roles(Collections.singletonList("ROLE_USER"))
        .build()
    );
  }

  /**
   * 유저 정보를 가져오는 데, null 이여도 상관 없음
   * @param userId
   * @return
   */
  public User get (String userId) {
    return userRepository.findById(userId);
  }

  /**
   * 유저 정보가 Null 이면 Exception 처리, 아니면 유저 정보 반환
   * @param userId : User의 login ID
   * @return
   * @throws UserIdNotFoundException : 유저 정보 탐색에 대한 실패 처리
   */
  @Override
  public User loadUserByUsername(String userId) throws UserIdNotFoundException {
    return Optional.ofNullable(get(userId)).orElseThrow(UserIdNotFoundException::new);
  }

  /**
   * 즐겨찾기 토글 처리
   * @param userId : 유저 아이디
   * @param videoIdx : 즐겨찾기 토글의 target video
   */
  public void setBookmark(String userId, long videoIdx) {
    User user = get(userId); // userId로 부터 회원 정보를 가져옴
    Video video = videoRepository.findByIdx(videoIdx); // videoIdx로부터 Video 정보를 가져옴
    List<Video> bookmark = user.getBookmark(); // user가 가지고 있는 bookmark video를 가져옴
    if (bookmark.contains(video)) { // bookmark에 video가 포함되어 있다면
      bookmark.remove(video); // 북마크 해제
    } else {
      bookmark.add(video); // 포함이 안 되어 있다면 북마크
    }
    userRepository.save(user); // 현재 상태 저장
  }
}
