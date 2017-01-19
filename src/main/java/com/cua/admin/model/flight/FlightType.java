package com.cua.admin.model.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor
public enum FlightType {
    
    DCO("Doble Comando"),
    AVS("Alumno vuelo solo"),
    ENT("Entrenamiento"),
    INS("Instrucción");
    
    private final String description;
}
