package tcss556.entities;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tcss556.services.models.UserGroup;

import javax.persistence.*;
import java.io.Serializable;

/** User entity class */
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table
public class UserEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Getter @Setter private String username;
  @Getter @Setter private String password;
  @Getter @Setter private String email;
  @Getter @Setter private Integer privilege;

  @Enumerated(EnumType.STRING)
  @Getter
  @Setter
  @Builder.Default
  private UserGroup userGroup = UserGroup.USER;

  @Getter @Setter private Integer floor;
  @Getter @Setter private Double location_x;
  @Getter @Setter private Double location_y;
  @Getter @Setter private String department;
}
