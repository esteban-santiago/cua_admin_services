/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    private Integer id;

    private Integer firstOrderGrouper;
    private Integer secondOrderGrouper;
    private Integer thirdOrderGrouper;
    private Integer fourthOrderGrouper;

    private String description;

    public String toFormattedString() {
        return firstOrderGrouper + "/"
                + secondOrderGrouper + "/"
                + thirdOrderGrouper + "/"
                + "0000" + fourthOrderGrouper;
    }

}
