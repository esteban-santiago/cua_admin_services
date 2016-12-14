/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.core;

/**
 *
 * @author esteban_santiago
 */
public enum IdentityDocumentType {
    DNI("DNI"), CUIL("CUIL"), CUIT("CUIT"), PASSPORT("Pasaporte");
    
    private String description;
    
    IdentityDocumentType() {
        
    }
    
    IdentityDocumentType(String description) {
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
