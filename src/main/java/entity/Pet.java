/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mich5
 */
@Entity
@Table(name = "pet")
@NamedQueries({
    @NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p")
    , @NamedQuery(name = "Pet.findAllDTO", query = "SELECT NEW dto.PetDTO(p.id, p.name, p.birth, p.species, p.death, p.owner.firstName) FROM Pet p")
    , @NamedQuery(name = "Pet.findAllDTOAlive", query = "SELECT NEW dto.PetDTO(p.id, p.name, p.birth, p.species, p.death, p.owner.firstName) FROM Pet p WHERE p.death IS NULL")
    , @NamedQuery(name = "Pet.findAllDTOByEvent", query = "SELECT NEW dto.PetDTO(p.id, p.name, p.birth, p.species, p.death, p.owner.firstName, e.id, e.event, e.remark, e.date) FROM Pet p LEFT JOIN p.eventCollection as e GROUP BY e.id HAVING e.date = :date")
    , @NamedQuery(name = "Pet.findById", query = "SELECT p FROM Pet p WHERE p.id = :id")
    , @NamedQuery(name = "Pet.findByName", query = "SELECT p FROM Pet p WHERE p.name = :name")
    , @NamedQuery(name = "Pet.findByBirth", query = "SELECT p FROM Pet p WHERE p.birth = :birth")
    , @NamedQuery(name = "Pet.findBySpecies", query = "SELECT p FROM Pet p WHERE p.species = :species")
    , @NamedQuery(name = "Pet.findByDeath", query = "SELECT p FROM Pet p WHERE p.death = :death")})
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "species", nullable = false, length = 45)
    private String species;
    @Column(name = "death")
    @Temporal(TemporalType.DATE)
    private Date death;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Collection<Event> eventCollection;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private Owner owner;

    public Pet() {
    }

    public Pet(Integer id) {
        this.id = id;
    }

    public Pet(Integer id, String name, Date birth, String species) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.species = species;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pet)) {
            return false;
        }
        Pet other = (Pet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pet[ id=" + id + " ]";
    }

}
