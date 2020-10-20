/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;
import entities.Phone;
import java.util.List;

/**
 *
 * @author ckfol
 */
public class PersonDTO {
    private Integer id;
    private String fName;
    private String lName;
    private List<Phone> phone;
    private String street;
    private String city;
    private int zip;

    
    public PersonDTO(Person p) {
        this.id = p.getId();
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhoneList();
        this.street = p.getAId().getStreet();
        this.city = p.getAId().getZipCode().getCity();
        this.zip = p.getAId().getZipCode().getZipCode();
        

    }
}
