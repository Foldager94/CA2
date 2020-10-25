/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Hobby;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ckfol
 */
public class HobbyDTO {
    
    private int id;
    private String name;
    private String wikilink;
    private String category;
    private String type;
    private List<PersonDTO> personList;

    public HobbyDTO(Hobby h) {
        this.id = h.getId();
        this.name = h.getName();
        this.wikilink = h.getWikiLink();
        this.category = h.getCategory();
        this.type = h.getType();
        this.personList = new ArrayList();
        
         h.getPersonList().forEach(person -> {
            this.personList.add(new PersonDTO(person));
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PersonDTO> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonDTO> personList) {
        this.personList = personList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikilink() {
        return wikilink;
    }

    public void setWikilink(String wikilink) {
        this.wikilink = wikilink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    
}
