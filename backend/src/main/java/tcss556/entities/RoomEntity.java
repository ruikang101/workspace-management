package tcss556.entities;

//import lombok.Builder;
//import lombok.Data;
//import lombok.With;

//@Data
//@Builder
//public class RoomEntity {

//    @With
//    private Long id;
//}

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class RoomEntity implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "room_id")
    private Integer roomId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "capacity")
    private Integer capacity;
    
   
    @Column(name = "type")
    private String type;
    
    @Column(name = "floor")
    private Integer floor;
    
    @Column(name = "b_x_coordinate")
    private double bxCoordinate;
    
    @Column(name = "b_y_coordinate")
    private double byCoordinate;
    
    @Column(name = "t_x_coordinate")
    private double txCoordinate;
    
    @Column(name = "t_y_coordinate")
    private double tyCoordinate;
    
    public RoomEntity() {
        super();
    }

    public RoomEntity(Integer roomId, String name, Integer capacity, String type, Integer floor, double bxCoordinate, double byCoordinate, double txCoordinate, double tyCoordinate) {
        super();
        this.roomId = roomId;
        this.name = name;
        this.capacity=capacity;
        this.type=type;
        this.floor=floor;
        this.bxCoordinate=bxCoordinate;
        this.byCoordinate=byCoordinate;
        this.txCoordinate=txCoordinate;
        this.tyCoordinate=tyCoordinate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getRoomId() {
        return roomId;
    }

    public void setMeetingId(Integer roomId) {
    	this.roomId = roomId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    
    public double getBxCoordinate() {
        return bxCoordinate;
    }

    public void setBxCoordinate(double bxCoordinate) {
        this.bxCoordinate = bxCoordinate;
    }
    
    public double getByCoordinate() {
        return byCoordinate;
    }

    public void setByCoordinate(Integer byCoordinate) {
        this.byCoordinate = byCoordinate;
    }
    
    public double getTxCoordinate() {
        return txCoordinate;
    }

    public void setTxCoordinate(Integer txCoordinate) {
        this.txCoordinate = txCoordinate;
    }
    
    public double getTyCoordinate() {
        return tyCoordinate;
    }

    public void setTyCoordinate(Integer tyCoordinate) {
        this.byCoordinate = tyCoordinate;
    }
    
}

