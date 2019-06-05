package facade;

import dto.EventDTO;
import dto.PetDTO;
import entity.Event;
import entity.Pet;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PetFacade {

    EntityManagerFactory emf;

    public PetFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<PetDTO> getAllPets() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Pet.findAllDTO", PetDTO.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<PetDTO> getAllPetsAlive() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Pet.findAllDTOAlive", PetDTO.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<PetDTO> getAllPetsByEvent(Date date) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Event.findDTOByDate").setParameter("date", date).getResultList();
        } finally {
            em.close();
        }
    }

    public void createEvent(EventDTO event) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Event e = new Event();
            e.setEvent(event.getEvent());
            e.setRemark(event.getRemark());
            e.setDate(event.getDate());
            Pet pet = getPetById(event.getPetID());
            e.setPet(pet);
            pet.getEventCollection().add(e);
            em.persist(e);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public EventDTO getEventByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Event.findDTOById", EventDTO.class).setParameter("id", id).getSingleResult();
        } finally {
            em.close();
        }
    }

    private Pet getPetById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Pet.findById", Pet.class).setParameter("id", id).getSingleResult();
        } finally {
            em.close();
        }
    }

}
