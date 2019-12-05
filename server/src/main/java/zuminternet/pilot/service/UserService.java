package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zuminternet.pilot.entity.User;
import zuminternet.pilot.repository.UserRepository;
import java.util.Collections;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User fetch (HashMap params) {
    String id = params.get("id").toString(),
           pw = params.get("pw").toString();
    User user = userRepository.findById(id);
    boolean confirmPassword = passwordEncoder.matches(pw, user.getPw());
    return confirmPassword ? user : null;
  }

  public boolean insert (HashMap params) {
    String id = params.get("id").toString();
    String pw = passwordEncoder.encode(params.get("pw").toString());
    String name = params.get("name").toString();
    long count = userRepository.countAllById(id);
    if (count > 0) {
      return false;
    }
    userRepository.save(
      User
        .builder()
        .id(id)
        .pw(pw)
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

  public int getUserIdx (String userId) {
    User user = userRepository.findById(userId);
    return user.getIdx().intValue();
  }
}
