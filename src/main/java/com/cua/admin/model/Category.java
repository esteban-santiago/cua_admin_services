/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author esteban_santiago
 */
@ToString
@EqualsAndHashCode

@Entity
@Table(schema="nextg", name="category")
public class Category implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_seq" )
    @GenericGenerator(
        name = "category_seq", 
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", 
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence", 
                value = "nextg.category_seq"
            ) 
    })
    @Id
    private Integer id;
    private String description;
    
    public Category() {
    }
    
    public Category(String description) {
        this.description = description;
    }

    public Category(Integer id, String description) {
        this.id = id;
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
}
