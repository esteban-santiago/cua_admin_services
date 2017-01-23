package com.cua.admin.model.operation.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor
public enum FlightPurpose {
    INS("Instrucción"),
    VP("Vuelo Privado"),
    ADA("Adaptación"),
    REA("Readaptación"),
    CON("Vuelo Contratado"),
    EXA("Exámen");
    
    private final String description;
}
