package zuminternet.pilot.entity;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@ToString
@Table(name="user")
public class User {
  @Id
  @GeneratedValue
  private Long idx;

  private String id, pw, name;

  @Builder
  public User (String id, String pw, String name) {
    this.id = id;
    this.pw = pw;
    this.name = name;
  }
}
