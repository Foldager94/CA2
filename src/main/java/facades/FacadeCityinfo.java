/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.CityinfoDTO;
import dtos.ListeDTO;
import entities.Cityinfo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicol
 */
public class FacadeCityinfo {

    private static FacadeCityinfo instance;
    private static EntityManagerFactory emf;


    //Private Constructor to ensure Singleton
    public static FacadeCityinfo getFacadeCityinfo(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeCityinfo();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ListeDTO getAllCityinfo() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cityinfo> query = em.createQuery("SELECT c FROM Cityinfo c", Cityinfo.class);
            return new ListeDTO(query.getResultList());
        } finally {
            em.close();
        }
      
    }

}
