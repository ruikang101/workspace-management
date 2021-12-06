package tcss556.services;

import org.springframework.web.bind.annotation.*;
import tcss556.dao.RoomRepository;
import tcss556.services.models.RoomData;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rooms")
public class RoomServices {

    @Resource
    private RoomRepository repository;

    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public RoomData createRoom(@RequestBody RoomData request) {
        System.out.println("receive request ;--------");
        System.out.println(request);
        return RoomData.builder().id(1).name("testRoom").b_x_coordinate(1.0).t_x_coordinate(11.0).b_y_coordinate(112.1).t_y_coordinate(12.32).capacity(122).floor(1).type(RoomData.RoomType.CONFERENCE).build();
    }


}
