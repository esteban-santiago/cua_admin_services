/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.core;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author esteban_santiago
 */
@ToString
@EqualsAndHashCode
//@Entity
//@Table(name = "identity_document")
@Embeddable
public class IdentityDocument implements Serializable {
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    private String identityDocumentNumber;

    public IdentityDocument() {
    }
    
    
    public IdentityDocument(String identityDocumentNumber) {
        this.identityDocumentNumber = identityDocumentNumber;
    }

    public IdentityDocument(IdentityDocumentType identityDocumentType, String identityDocumentNumber) {
        this.identityDocumentType = identityDocumentType;
        this.identityDocumentNumber = identityDocumentNumber;
    }
    

    /**
     * @return the identityDocumentType
     */
    public IdentityDocumentType getIdentityDocumentType() {
        return identityDocumentType;
    }

    /**
     * @param identityDocumentType the identityDocumentType to set
     */
    public void setIdentityDocumentType(IdentityDocumentType identityDocumentType) {
        this.identityDocumentType = identityDocumentType;
    }

    /**
     * @return the identityDocumentNumber
     */
    public String getIdentityDocumentNumber() {
        return identityDocumentNumber;
    }

    /**
     * @param identityDocumentNumber
     */
    public void setDocumentNumber(String identityDocumentNumber) {
        this.identityDocumentNumber = identityDocumentNumber;
    }
}
