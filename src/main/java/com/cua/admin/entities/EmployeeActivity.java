/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author esteban_santiago
 */
@Entity
@Table(schema="nextg", name="employee_activity")
public class EmployeeActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_activity_seq_gen" )
    @SequenceGenerator(schema = "nextg", name="employee_activity_seq_gen",sequenceName="nextg.employee_activity_seq_gen")
    private Integer id;
    private String description;

    public EmployeeActivity() {
        
    }
    
    
    public EmployeeActivity(String activity) {
        this.description = activity;
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
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
    
    @Override
    public String toString() {
        return "[id: " + id + ", description: " + description + "]"; 
    }    
}
