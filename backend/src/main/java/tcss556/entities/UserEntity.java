package tcss556.entities;

//import lombok.Data;

//@Data
//public class UserEntity {

//   private Long id;
//}


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class UserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    private Long id;
	
    @Column(name = "user_id")
    private Integer userId;
	
    @Column(name = "user_name")
    private String userName;
	
    @Column(name = "email")
    private String email;
	
    @Column(name = "password")
    private String password;
	
    @Column(name = "group")
    private String group;
	
    @Column(name = "privilege")
    private Integer privilege;
    
    @Column(name = "floor")
    private Integer floor;
    
    @Column(name = "location_x")
    private double locationX;
    
    @Column(name = "location_y")
    private double locationY;
    
    public UserEntity() {
        super();
    }

    public UserEntity(Integer userId, String userName, String email, String password, String group,Integer privilege, Integer floor, double locationX, double locationY) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.email=email;
        this.password=password;
        this.group=group;
        this.privilege=privilege;
        this.floor=floor;
        this.locationX=locationX;
        this.locationY=locationY;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }
    
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    
    public double getLocationx() {
        return locationX;
    }

    public void setLocationx(double locationX) {
        this.locationX = locationX;
    }
    
    public double getLocationy() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }
   
}
