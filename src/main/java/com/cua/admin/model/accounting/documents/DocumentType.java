/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting.documents;

import javax.persistence.Embeddable;

/**
 *
 * @author esteban_santiago
 */
public enum DocumentType {
    NCE("Nota de Crédito Emitida"), 
    FVE("Ficha de Vuelo Emitida"), 
    OPE("Orden de Pago Emitida"), 
    CSE("Cuota Social Emitida"),
    RCE("Recibo Emitido");

    private String description;
    
       DocumentType(String description) {
           this.description = description;
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
