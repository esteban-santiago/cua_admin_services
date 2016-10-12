/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author esteban_santiago
 */
@Entity
@Table(schema="nextg", name="person_category")
public class PersonCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String description;
    
    public PersonCategory() {
    }
    
    public PersonCategory(String description) {
        this.description = description;
        
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
    public String getDescription() {
        return description;
    }

    /**
     * @param description the category to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "[id: " + id + ", category: " + description + "]"; 
    }
}
