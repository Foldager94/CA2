package facades;

import dtos.PersonDTO;
import entities.Cityinfo;
import entities.Person;
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
    private PersonDTO p1;
    private PersonDTO p2;
    private PersonDTO p3;
    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = FacadePerson.getFacadePerson(emf);
        
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Cityinfo info = new Cityinfo(2510, "herlev");
        Person p = new Person("Jon", "papi", "kalkun@lol.com");
        try {
            em.getTransaction().begin();

            em.createQuery("DELETE from Phone").executeUpdate();
            em.createQuery("DELETE from Person").executeUpdate();
            em.createQuery("DELETE from Address").executeUpdate();
            em.createQuery("DELETE from Cityinfo").executeUpdate();
            em.persist(info);

            em.getTransaction().commit();
            em.getTransaction().begin();
            p1 = facade.addPerson("jon", "papi", "kalkun@lole.com", 5214584, "arbejds", "aleris", 2510, "ST.V");
            p2 = facade.addPerson("Jan", "pop", "kka@lol.com", 20255555, "Hjemme", "hamlet", 2510, "ST.h");
            p3 = facade.getPersonByID(p1.getId());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetPersonByPhone() {
        assertEquals(facade.getPersonByPhone(20255555).getPhone(), p2.getPhone());
    }

    @Test
    public void testGetPersonByID() {
        assertEquals(p2.getId(), facade.getPersonByID(p2.getId()).getId());
    }
    
    @Test
    public void testEditPerson(){
        assertEquals(facade.editPerson(p1).getId(), p3.getId());
    }
 @Test
    public void testEditPerson2(){
        assertNotEquals(facade.editPerson(p1).getId(), p3.getId());
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
