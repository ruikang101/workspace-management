package tcss556.entities;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class MeetingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Getter @Setter private Long hostId;
  @Getter @Setter private Date startTime;
  @Getter @Setter private Date endTime;
  @Getter @Setter private Long roomId;

  @Getter @Setter @ElementCollection private List<Long> guests;
}
