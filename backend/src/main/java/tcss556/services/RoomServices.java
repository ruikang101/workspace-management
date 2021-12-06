package tcss556.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomServices {

    @GetMapping("/{roomId}")
    public String echoRoomId(@PathVariable("roomId") String roomId) {
        return String.format("Room : %s", roomId);
    }
    @GetMapping("/room/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        return String.format("Hello %s!", name);
    }
}
