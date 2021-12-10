package tcss556.entities;

import lombok.*;
import tcss556.services.models.UserGroup;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter @Setter private Long id;
  @Getter @Setter private String username;
  @Getter @Setter private String password;
  @Getter @Setter private String email;
  @Getter @Setter private Integer privilege;
  @Enumerated(EnumType.STRING)
  @Builder.Default private UserGroup userGroup = UserGroup.USER;
  @Getter @Setter private Integer floor;
  @Getter @Setter private Double location_x;
  @Getter @Setter private Double location_y;
}
