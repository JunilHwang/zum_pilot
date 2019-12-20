package zuminternet.pilot.domain.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
  @Id
  @GeneratedValue
  private Long idx;

  @Column(nullable = false, unique = true, length = 255)
  private String id;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(nullable = false, unique = true, length = 255)
  private String pw;

  @Column(nullable = false, unique = true, length = 255)
  private String name;

  @ManyToMany
  protected List<Video> bookmark;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ElementCollection(fetch = FetchType.EAGER)
  @Builder.Default
  private List<String> roles = new ArrayList();

  @Transient
  @Setter
  private String token;

  @Override
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

  }
  @Override
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public String getPassword() {
    return null;
  }

  @Override
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public String getUsername() {
    return this.id;
  }

  @Override
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public boolean isEnabled() {
    return true;
  }
}
