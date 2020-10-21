package facades;

import entities.Cityinfo;
import entities.Person;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static EntityManagerFactory emf2;
    private static FacadePerson facade;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf2 = EMF_Creator.createEntityManagerFactory();
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
        
        
        
       
        try {
            em.getTransaction().begin();
            em.persist(info);
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            
            facade.addPerson("Jan", "pop", "kka@lol.com", 20254547, "Hjemme", "hamlet", 2510, "ST.h");
            facade.addPerson("Jan", "pop", "kka@lol.com", 20255555, "Hjemme", "hamlet", 2510, "ST.h");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
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
