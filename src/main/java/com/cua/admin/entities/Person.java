/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author esteban_santiago
 */

@Entity
@Table(schema="nextg", name="person")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_seq_gen" )
    @SequenceGenerator(schema = "nextg", name="person_seq_gen",sequenceName="nextg.person_seq_gen")
    private Integer id;
    private String name;
    
    //@Temporal(TemporalType.DATE)
    @Column
    private LocalDate dateOfCreation; //Fecha de Ingreso
    private LocalDate dateOfBirth; //Fecha de nacimiento
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nationality_id", foreignKey = @ForeignKey(name = "nationality_id_fk"))
    private Nationality nationality; //Nacionalidad
    private String identificationDocument; //Documento de identidad
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id_fk"))
    private Address address;
    
    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id_fk"))
    private PersonCategory category;


    
    
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
    public PersonCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(PersonCategory category) {
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
    
    @Override
    public String toString() {
        return "id: " + id + " name: " + getName();
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
}
