package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.Cityinfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;

    private static FacadePerson facade_person;
    private static FacadeHobby facade_hobby;

    private PersonDTO pDTO1;
    private PersonDTO pDTO2;
    private PersonDTO p3;
    private PersonDTO p4;
    private HobbyDTO hDTO1;
    private HobbyDTO hDTO2;
    private Hobby h1;
    private Hobby h2;
    private Hobby h3;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade_person = FacadePerson.getFacadePerson(emf);
        facade_hobby = FacadeHobby.getFacadeHobby(emf);

        EntityManager em = emf.createEntityManager();

        Cityinfo info = new Cityinfo(2510, "herlev");
        em.getTransaction().begin();
        em.persist(info);
        em.getTransaction().commit();

    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("kalkun@lol.com", "Jon", "papi");
        Person p2 = new Person("kylling@lol.com", "Jane", "mutti");
        h1 = new Hobby("Fægtning", "wiki.com", "Bøssesport", "Sovende");
        h2 = new Hobby("Skydning", "wiki.com", "Bøssesport", "Dansende");
        h3 = new Hobby("TræFældning", "wiki.com", "Endnu mere Bøssesport", "Stående");
        Address a = new Address("Overgade", "13", 2510);
        p1.setAId(a);
        p1.addPhone(new Phone(32663266, "Arbejde"));
        p2.setAId(a);
        p2.addPhone(new Phone(45771817, "Arbejde"));
        pDTO1 = new PersonDTO(p1);
        pDTO2 = new PersonDTO(p2);

        try {
            em.getTransaction().begin();

            em.createQuery("DELETE from Phone").executeUpdate();
            em.createQuery("DELETE from Person").executeUpdate();
            em.createQuery("DELETE from Address").executeUpdate();
            em.createQuery("DELETE from Hobby").executeUpdate();
            //em.createQuery("DELETE from Cityinfo").executeUpdate();
            // em.persist(p);

            em.persist(h1);
            em.persist(h2);
            em.getTransaction().commit();
            hDTO1 = new HobbyDTO(h1);
            hDTO2 = new HobbyDTO(h2);
            pDTO1 = facade_person.addPerson(pDTO1);
            pDTO2 = facade_person.addPerson(pDTO2);
            hDTO1 = facade_hobby.addHobbyToPerson(pDTO1, hDTO1);
            hDTO2 = facade_hobby.addHobbyToPerson(pDTO2, hDTO2);

            p3 = facade_person.getPersonByID(pDTO1.getId());
        } catch (Exception e) {
            e.getMessage();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetPersonByPhone() {
        assertEquals(facade_person.getPersonByPhone(32663266).getId(), pDTO1.getId());
    }

    @Test
    public void testGetPersonByID() {
        assertEquals(pDTO1.getId(), facade_person.getPersonByID(pDTO1.getId()).getId());
    }

    @Test
    public void testEditPerson() {
        assertEquals(facade_person.editPerson(pDTO1).getId(), p3.getId());
    }

    @Test
    public void testEditPerson2() {
        assertNotEquals(facade_person.editPerson(pDTO1).getId(), pDTO2.getId());
    }

//    @Test
//    public void testAddHobbyToPerson() {
//        assertEquals();
//    }

    @Test
    public void testGetHobbyByName() {
        assertEquals(hDTO1.getId(), facade_hobby.getHobbyByName("Fægtning").getId());
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, 2, "Expects two rows in the database");
    }

}
