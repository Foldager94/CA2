/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Cityinfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicol
 */
public class ListeDTO {
    
    
      List<CityinfoDTO> alle = new ArrayList();
      
      
    public ListeDTO(List<Cityinfo> CityinfoEntities) {  
        CityinfoEntities.forEach((c) -> {
            alle.add(new CityinfoDTO(c));
        });
    }
}
