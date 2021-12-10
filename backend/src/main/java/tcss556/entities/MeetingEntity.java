package tcss556.entities;

//import lombok.Data;

//@Data
//public class MeetingEntity {

//    private Long id;

//}

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "meeting")
public class MeetingEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    private Long id;
	
    @Column(name = "meeting_id")
    private Integer meetingId;
	
    @Column(name = "host_id")
    private Integer hostId;
	
    @Column(name = "room_id")
    private Integer roomId;
	
    @Column(name = "start_time")
    private Date startTime;
	
    @Column(name = "end_time")
    private Date endTime;
	
    
    
    public MeetingEntity() {
        super();
    }

    public MeetingEntity(Integer meetingId, Integer hostId, Integer roomId, Date startTime, Date endTime) {
        super();
        this.meetingId = meetingId;
        this.hostId = hostId;
        this.roomId=roomId;
        this.startTime=startTime;
        this.endTime=endTime;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }
    
    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }
    
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
}

