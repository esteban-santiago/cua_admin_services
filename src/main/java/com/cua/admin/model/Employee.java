/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 *
 * @author esteban_santiago
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema="nextg", name="employee")
public class Employee extends Person {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "activity_id_fk"))
    private Activity activity;

    public Employee() {
        
    }
    
    /**
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
