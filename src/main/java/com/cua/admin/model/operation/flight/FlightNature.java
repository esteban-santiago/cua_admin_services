package com.cua.admin.model.operation.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor

public enum FlightNature {
    LDI("Local Diurno"),
    TDI("Travesía Diurna"),
    LNO("Local Nocturno"),
    TNO("Travesía Nocturna"),
    ETV("Simulador");
    
    private final String description;
    
}
