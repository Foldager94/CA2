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
public class CityinfoDTO {
  
    private int zipCode;
    private String city;

    public CityinfoDTO(Cityinfo c) {
        this.zipCode = c.getZipCode();
        this.city = c.getCity();
    }
    
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
 

    
    
}
