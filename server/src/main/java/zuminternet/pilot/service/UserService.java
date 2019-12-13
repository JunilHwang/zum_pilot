package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.dao.entity.User;
import zuminternet.pilot.domain.dao.entity.Video;
import zuminternet.pilot.domain.dao.repository.UserRepository;
import zuminternet.pilot.domain.dao.repository.VideoRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final VideoRepository videoRepository;
  private final PasswordEncoder passwordEncoder;

  public User fetch (HashMap params) {
    String id = params.get("id").toString(),
           pw = params.get("pw").toString();
    return this.fetch(id, pw);
  }

  public User fetch (String id, String pw) {
    User user = userRepository.findById(id);
    boolean confirm = false;
    if (user != null) {
      confirm = passwordEncoder.matches(pw, user.getPw());
    }
    return confirm ? user : null;
  }

  public User fetch (String id) {
    return userRepository.findById(id);
  }

  public boolean insert (HashMap params) {
    String id = params.get("id").toString();
    String pw = params.get("pw").toString();
    String name = params.get("name").toString();
    return this.insert(id, pw, name);
  }

  public boolean insert (String id, String pw, String name) {
    String encodedPw = passwordEncoder.encode(pw);
    long count = userRepository.countAllById(id);
    if (count > 0) {
      return false;
    }
    userRepository.save(
      User
        .builder()
        .id(id)
        .pw(encodedPw)
        .name(name)
        .roles(Collections.singletonList("ROLE_USER"))
        .build()
    );
    return true;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findById(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return user;
  }

  public User get (String userId) {
    return userRepository.findById(userId);
  }

  public void setBookmark(String userId, long videoIdx) {
    User user = this.get(userId);
    Video video = videoRepository.findByIdx(videoIdx);
    if (user.getBookmark().contains(video)) {
      user.getBookmark().remove(video);
    } else {
      user.getBookmark().add(video);
    }
    userRepository.save(user);
  }

  public List<Video> getBookmark(String userId) {
    return this.get(userId).getBookmark();
  }
}
