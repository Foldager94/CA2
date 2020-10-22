package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Cityinfo;
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

    private static FacadePerson facade;
    private PersonDTO pDTO1;
    private PersonDTO pDTO2;
    private PersonDTO p3;
    private PersonDTO p4;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = FacadePerson.getFacadePerson(emf);
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
            //em.createQuery("DELETE from Cityinfo").executeUpdate();
            // em.persist(p);
            em.getTransaction().commit();

            pDTO1 = facade.addPerson(pDTO1);
            pDTO2 = facade.addPerson(pDTO2);
            p3 = facade.getPersonByID(pDTO1.getId());
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetPersonByPhone() {
        assertEquals(facade.getPersonByPhone(32663266).getId(), pDTO1.getId());
    }

    @Test
    public void testGetPersonByID() {
        assertEquals(pDTO1.getId(), facade.getPersonByID(pDTO1.getId()).getId());
    }

    @Test
    public void testEditPerson() {
        assertEquals(facade.editPerson(pDTO1).getId(), p3.getId());
    }

    @Test
    public void testEditPerson2() {
        assertNotEquals(facade.editPerson(pDTO1).getId(), pDTO2.getId());
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
