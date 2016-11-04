/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 *
 * @author esteban_santiago
 */

@ToString
@EqualsAndHashCode
@Entity
@Table(name="account")
public class Account implements Serializable {
    @Id
    private Integer id;
    private Integer firstOrderGrouper;
    private Integer secondOrderGrouper;
    private Integer thirdOrderGrouper;
    private Integer fourthOrderGrouper;
    private String description;

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
     * @return the firstOrderGrouper
     */
    public Integer getFirstOrderGrouper() {
        return firstOrderGrouper;
    }

    /**
     * @param firstOrderGrouper the firstOrderGrouper to set
     */
    public void setFirstOrderGrouper(Integer firstOrderGrouper) {
        this.firstOrderGrouper = firstOrderGrouper;
    }

    /**
     * @return the secondOrderGrouper
     */
    public Integer getSecondOrderGrouper() {
        return secondOrderGrouper;
    }

    /**
     * @param secondOrderGrouper the secondOrderGrouper to set
     */
    public void setSecondOrderGrouper(Integer secondOrderGrouper) {
        this.secondOrderGrouper = secondOrderGrouper;
    }

    /**
     * @return the thirdOrderGrouper
     */
    public Integer getThirdOrderGrouper() {
        return thirdOrderGrouper;
    }

    /**
     * @param thirdOrderGrouper the thirdOrderGrouper to set
     */
    public void setThirdOrderGrouper(Integer thirdOrderGrouper) {
        this.thirdOrderGrouper = thirdOrderGrouper;
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

    /**
     * @return the fourthOrderGrouper
     */
    public Integer getFourthOrderGrouper() {
        return fourthOrderGrouper;
    }

    /**
     * @param fourthOrderGrouper the fourthOrderGrouper to set
     */
    public void setFourthOrderGrouper(Integer fourthOrderGrouper) {
        this.fourthOrderGrouper = fourthOrderGrouper;
    }
    
}
