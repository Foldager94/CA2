/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Person;
import entities.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ckfol
 */
public class FacadePerson {
    
    private static FacadePerson instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadePerson() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadePerson getFacadePerson(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadePerson();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    
    // Find person in DB by PhoneNumber
    // ToDo: Exception
    public PersonDTO getPersonByPhone(int phoneNumber){
        EntityManager em = getEntityManager();
        try{
            Phone phoneObject = em.find(Phone.class, phoneNumber);
            Person person = em.find(Person.class, phoneObject.getPId());
            if (person == null) {
                System.out.println("Person ikke fundet på telefonnummer");
                throw new NullPointerException();
                //throw new PersonNotFoundException("Requested Person does not exist");
            } else {
                return new PersonDTO(person);
            }
        }finally{
            em.close();
        }
        
    }
    
    
    
    
}
