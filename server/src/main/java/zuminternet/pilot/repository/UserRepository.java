package zuminternet.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuminternet.pilot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> { }
