package tcss556.entities;



public class RoomEntity {


import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
public class RoomEntity {

    @With

    private Long id;
}
