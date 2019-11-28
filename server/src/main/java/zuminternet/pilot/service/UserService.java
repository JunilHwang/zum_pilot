package zuminternet.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zuminternet.pilot.entity.User;
import zuminternet.pilot.repository.UserRepository;

import java.util.HashMap;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  public User fetch (HashMap params) {
    return userRepository.findByIdAndPw(
      params.get("id").toString(),
      params.get("pw").toString()
    );
  }

  public boolean insert (HashMap params) {
    String id = params.get("id").toString();
    String pw = params.get("pw").toString();
    String name = params.get("name").toString();
    long count = userRepository.countAllById(id);
    if (count != 0) {
      return false;
    }
    userRepository.save(
      User
        .builder()
        .id(id)
        .pw(pw)
        .name(name)
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
}
