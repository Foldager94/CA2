package facades;

import dtos.PhoneDTO;
import entities.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class FacadePhone {

    private static FacadePhone instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadePhone() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadePhone getFacadePhone(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadePhone();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean phoneExists(PhoneDTO phoneDTO) {

        boolean exists = false;
        EntityManager em = emf.createEntityManager();
        Phone phone = em.find(Phone.class, phoneDTO.getNumber());

        if (phone != null) {
            exists = true;
        }

        return exists;
    }

}
