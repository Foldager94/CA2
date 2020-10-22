/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ckfol
 */
public class PersonDTO {

    private Integer id;
    private String fName;
    private String lName;
    private String email;
    private List<PhoneDTO> phoneList;
    private String street;
    private String additionalInfo;
    private String city;
    private int zip;

    public PersonDTO(Person p) {
        this.id = p.getId();
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.email = p.getEmail();
        this.phoneList = new ArrayList<>();

        for (Phone phone : p.getPhoneList()) {
            this.phoneList.add(new PhoneDTO(phone));
        }
        
        this.street = p.getAId().getStreet();
        this.additionalInfo = p.getAId().getAdditionalInfo();
        this.city = p.getAId().getZipCode().getCity();
        this.zip = p.getAId().getZipCode().getZipCode();

    }

    public List<PhoneDTO> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<PhoneDTO> phoneList) {
        this.phoneList = phoneList;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

}
