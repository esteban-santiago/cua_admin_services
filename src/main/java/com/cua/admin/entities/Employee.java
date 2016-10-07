/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author esteban_santiago
 */
@Entity
@Table(name="employee")
public class Employee extends Person {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "activity_id_fk"))
    private EmployeeActivity activity;

    public Employee() {
        
    }
    
    /**
     * @return the activity
     */
    public EmployeeActivity getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(EmployeeActivity activity) {
        this.activity = activity;
    }
}
