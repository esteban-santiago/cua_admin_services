/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public EmployeeActivity(Integer id, String activity) {
        this.id = id;
        this.description = activity;
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
    
    @Override
    public String toString() {
        return "EmployeeActivity = [id: " + id + ", description: " + description + "]"; 
    }   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeActivity other = (EmployeeActivity) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
