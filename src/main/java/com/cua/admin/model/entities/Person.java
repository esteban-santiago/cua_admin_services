/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 *
 * @author esteban_santiago
 */
@ToString
@EqualsAndHashCode
@Entity
@Table(schema="nextg", name="person")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_id_seq" )
    @SequenceGenerator(name="person_id_seq", sequenceName="nextg.person_id_seq",allocationSize=1)
    @Id
    private Integer id;
    private String name;
    
    @Column
    private LocalDate dateOfCreation; //Fecha de Ingreso
    private LocalDate dateOfBirth; //Fecha de nacimiento
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "nationality_id", foreignKey = @ForeignKey(name = "nationality_id_fk"))
    private Nationality nationality; //Nacionalidad
    
    private String identificationDocument; //Documento de identidad
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "address_id_fk"))
    private Set<Address> address = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id_fk"))
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "way_to_contact_id_fk"))
    private Set<WayToContact> wayToContact = new HashSet<>();
    
    public Person() {
    }
    
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
 
    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dateOfCreation
     */
    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * @param dateOfCreation the dateOfCreation to set
     */
    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    /**
     * @return the address
     */
    public Set<Address> getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void addAddress(Address address) {
        this.address.add(address);
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Set<Address> address) {
        this.address = address;
    }
    
    
    /**
     * @return the nationality
     */
    public Nationality getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the dateOfBirth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the wayToContact
     */
    public Set<WayToContact> getWayToContact() {
        return wayToContact;
    }

    /**
     * @param wayToContact the wayToContact to set
     */
    public void addWayToContact(WayToContact wayToContact) {
        this.wayToContact.add(wayToContact);
    }

    /**
     * @param wayToContact the wayToContact to set
     */
    public void setWayToContact(Set<WayToContact> wayToContact) {
        this.wayToContact = wayToContact;
    }

}
