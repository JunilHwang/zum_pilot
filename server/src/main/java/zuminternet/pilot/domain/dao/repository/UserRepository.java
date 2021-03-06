package zuminternet.pilot.domain.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuminternet.pilot.domain.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByIdAndPw(String id, String pw);
  long countAllById(String id);
  User findById(String id);
}
