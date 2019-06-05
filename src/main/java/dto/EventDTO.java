package dto;

import java.util.Date;

public class EventDTO {

    private Integer id;
    private String event;
    private String remark;
    private Date date;
    private Integer petID;

    public EventDTO(String event, String remark, Date date, Integer petID) {
        this.event = event;
        this.remark = remark;
        this.date = date;
        this.petID = petID;
    }

    public EventDTO(Integer id, String event, String remark, Date date) {
        this.id = id;
        this.event = event;
        this.remark = remark;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public String getRemark() {
        return remark;
    }

    public Date getDate() {
        return date;
    }

    public Integer getPetID() {
        return petID;
    }

}
