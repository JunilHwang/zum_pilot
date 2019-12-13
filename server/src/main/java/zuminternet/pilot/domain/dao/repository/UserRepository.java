package zuminternet.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuminternet.pilot.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByIdAndPw(String id, String pw);
  long countAllById(String id);
  User findById(String id);
}
