package zuminternet.pilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableCaching
@SpringBootApplication
public class PilotApplication {

  public static void main(String[] args) {
    SpringApplication.run(PilotApplication.class, args);
  }

  @Bean
  public PasswordEncoder passwordEncoder () {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
