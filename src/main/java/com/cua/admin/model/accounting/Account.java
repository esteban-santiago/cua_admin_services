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

    
    /**
     * Devuelve la cuenta en formato 0/0/0/0000.
     * 
     * @return String
     */
    public String toFormattedString() {
        return firstOrderGrouper + "/"
                + secondOrderGrouper + "/"
                + thirdOrderGrouper + "/"
                + String.format("%04d", fourthOrderGrouper);
    }

    /**
     * Devuelve la cuenta en formato 0/0/0/0000 
     * y agrega la descripción de la cuenta.
     * 
     * @return String
     */    
    public String toFormattedStringWithDescription() {
        return toFormattedString() + "-" + description;
    }

}
