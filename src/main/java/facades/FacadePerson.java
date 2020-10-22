/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.PhoneDTO;
import entities.Address;
import entities.Cityinfo;
import entities.Person;
import entities.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ckfol
 */
public class FacadePerson {

    private static FacadePerson instance;
    private static EntityManagerFactory emf;

    private FacadePhone facadePhone = FacadePhone.getFacadePhone(emf);
    
    //Private Constructor to ensure Singleton
    private FacadePerson() {
    }

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

    public static Cityinfo findZipCode(int zipcode) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Cityinfo.findByZipCode");
            q.setParameter("zipCode", zipcode);
            Cityinfo info = (Cityinfo) (q.getSingleResult());
            if (info.getZipCode() == 0) {
                System.out.println("Zipcode dosen't exist");
            }
            return info;
        } finally {
            em.close();
        }
    }

    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, p.getId());
        if (person == null) {

        }
        person.setFirstName(p.getfName());
        person.setLastName(p.getlName());
        person.setEmail(p.getEmail());
        person.getAId().setStreet(p.getStreet());
        person.getAId().getZipCode().setZipCode(p.getZip());
        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    // Find person in DB by PhoneNumber
    // ToDo: Exception
    public PersonDTO getPersonByPhone(int phoneNumber) {
        EntityManager em = getEntityManager();
        try {
            Phone phoneObject = em.find(Phone.class, phoneNumber);
            Person person = em.find(Person.class, phoneObject.getPId().getId());
            if (person == null) {
                System.out.println("Person ikke fundet på telefonnummer");
                throw new NullPointerException();
                //throw new PersonNotFoundException("Requested Person does not exist");
            } else {
                return new PersonDTO(person);
            }
        } finally {
            em.close();
        }

    }

    public PersonDTO addPerson(PersonDTO p) {
        if ((p.getfName().length() == 0) || (p.getlName().length() == 0)) {
            //throw new MissingInputException("first name or/and last name missing");
        }
        EntityManager em = emf.createEntityManager();
        Person person = new Person(p.getEmail(), p.getfName(), p.getlName());
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT a FROM Address a WHERE a.street = :street AND a.zipCode = :zip AND a.additionalInfo = :additionalInfo");
            q.setParameter("street", p.getStreet());
            q.setParameter("zip", findZipCode(p.getZip()));
            q.setParameter("additionalInfo", p.getAdditionalInfo());
            List<Address> adressess = q.getResultList();
            if (adressess.size() > 0) {
                person.setAId(adressess.get(0));
            } else {
                Address address = new Address(p.getStreet(), p.getAdditionalInfo(), p.getZip());
                person.setAId(address);
            }

            for (PhoneDTO phone : p.getPhoneList()) {
                if (!facadePhone.phoneExists(phone)) {
                    person.addPhone(new Phone(phone.getNumber(), phone.getDescription()));
                } 
            }

            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public PersonDTO getPersonByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Person PersonOBJ = em.find(Person.class, id);
            return new PersonDTO(PersonOBJ);
        } finally {
            em.close();
        }
    }

    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> personList = query.getResultList();
        em.close();
        return personList;
    }

}
