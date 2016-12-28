package com.cua.admin.model.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IdentityDocumentType {

    DNI("DNI"), CUIL("CUIL"), CUIT("CUIT"), PASSPORT("Pasaporte");
    
    private final String description;
    
}
