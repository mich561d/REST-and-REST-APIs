package dto;

import java.util.Date;

public class PetDTO {

    private Integer id;
    private String name;
    private Date birth;
    private String species;
    private Date death;
    private String owner;
    private String event;

    public PetDTO(Integer id, String name, Date birth, String species, Date death, String owner) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.species = species;
        this.death = death;
        this.owner = owner;
    }

    public PetDTO(Integer id, String name, Date birth, String species, Date death, String owner, Integer eventId, String event, String remark, Date date) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.species = species;
        this.death = death;
        this.owner = owner;
        this.event = eventId + ": " + event + "(" + remark + ") - " + date;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirth() {
        return birth;
    }

    public String getSpecies() {
        return species;
    }

    public Date getDeath() {
        return death;
    }

    public String getOwner() {
        return owner;
    }

    public String getEvent() {
        return event;
    }

}
