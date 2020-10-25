/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.Hobby;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nyxis
 */
public class FacadeHobby {

    private static FacadeHobby instance;
    private static EntityManagerFactory emf;

    private FacadePhone facadePhone = FacadePhone.getFacadePhone(emf);

    //Private Constructor to ensure Singleton
    private FacadeHobby() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeHobby getFacadeHobby(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeHobby();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public HobbyDTO addHobbyToPerson(PersonDTO persondto, HobbyDTO hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            Hobby h = em.find(Hobby.class, hobby.getId());
            Person person = em.find(Person.class, persondto.getId());
            person.addHobby(h);
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return new HobbyDTO(h);
        } finally {
            em.close();
        }

    }

    public HobbyDTO getHobbyByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :name", Hobby.class);
            query.setParameter("name", name);
            return new HobbyDTO(query.getSingleResult());
        } finally {
            em.close();
        }

    }
     public List<PersonDTO> getAllPersonsWithHobby(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :name", Hobby.class);
            query.setParameter("name", name);           
            HobbyDTO hobby = new HobbyDTO(query.getSingleResult());
            return hobby.getPersonList();
        } finally {
            em.close();
        }

    }
    

}
