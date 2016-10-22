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
@Table(schema="nextg", name="nationality")
public class Nationality implements Serializable {
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="nationality_seq" )
    @GenericGenerator(
        name = "nationality_seq", 
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", 
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence", 
                value = "nextg.nationality_seq"
            ) 
    })
    @Id
    private Integer id;
    private String description;

    public Nationality() {
        
    }
    
    public Nationality(String nationality) {
        this.description = nationality;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
