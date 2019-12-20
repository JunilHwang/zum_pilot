package zuminternet.pilot.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  /**
   * http 요청에 대해 처리하는 내용을 정의함
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure (HttpSecurity http) throws Exception {
    http
      .httpBasic().disable() // spring-security에서 제공하는 /login 과 같은 페이지 비활성
      .csrf().disable() // Cross site request forgery 비활성
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session을 stateless 형태로 관리
      .and()
         // jwt를 이용하는 filter 추가
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // filter 사용
  }

  /**
   * AuthenticationManager Bean 생성
   * @return
   * @throws Exception
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
