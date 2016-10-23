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
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author esteban_santiago
 */

@ToString
@EqualsAndHashCode
@Entity
@Table(schema="nextg", name="way_to_contact")
public class WayToContact implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="way_to_contact_seq" )
    @GenericGenerator(
        name = "way_to_contact_seq", 
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", 
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence", 
                value = "nextg.way_to_contact_seq"
            ) 
    })
    @Id
    private Integer id;
    private String typeOf;
    private String identificator;

    public WayToContact() {
        
    }
    
    public WayToContact(String typeOf, String identificator) {
        this.typeOf = typeOf;
        this.identificator = identificator;
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
     * @return the typeOf
     */
    public String getTypeOf() {
        return typeOf;
    }

    /**
     * @param typeOf the typeOf to set
     */
    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
    }

    /**
     * @return the identificator
     */
    public String getIdentificator() {
        return identificator;
    }

    /**
     * @param identificator the identificator to set
     */
    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }
    
}
