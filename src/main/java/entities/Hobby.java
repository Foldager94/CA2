/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ckfol
 */
@Entity
@Table(name = "HOBBY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h"),
    @NamedQuery(name = "Hobby.findById", query = "SELECT h FROM Hobby h WHERE h.id = :id"),
    @NamedQuery(name = "Hobby.findByName", query = "SELECT h FROM Hobby h WHERE h.name = :name"),
    @NamedQuery(name = "Hobby.findByWikiLink", query = "SELECT h FROM Hobby h WHERE h.wikiLink = :wikiLink"),
    @NamedQuery(name = "Hobby.findByCategory", query = "SELECT h FROM Hobby h WHERE h.category = :category"),
    @NamedQuery(name = "Hobby.findByType", query = "SELECT h FROM Hobby h WHERE h.type = :type")})
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "wikiLink")
    private String wikiLink;
    @Size(max = 45)
    @Column(name = "category")
    private String category;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @JoinTable(name = "PERSON_HOBBY", joinColumns = {
        @JoinColumn(name = "h_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "p_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Person> personList;

    public Hobby() {
    }

    public Hobby(String name, String wikiLink, String category, String type) {
        this.id = id;
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
        this.personList = new ArrayList<>();
    }
    
    

    public Hobby(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
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

    @XmlTransient
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hobby)) {
            return false;
        }
        Hobby other = (Hobby) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hobby[ id=" + id + " ]";
    }
    
}
