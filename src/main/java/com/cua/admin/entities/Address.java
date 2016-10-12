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
@Table(schema="nextg", name="address")
public class Address {
    @Id
    private int id;
    private String street;
    private String zip;
    private String city;
    private String province;
    private String country;
   
    public Address() {
        
    }
}
