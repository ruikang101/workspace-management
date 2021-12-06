package tcss556.entities;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
public class RoomEntity {

    @With
    private Long id;
}
