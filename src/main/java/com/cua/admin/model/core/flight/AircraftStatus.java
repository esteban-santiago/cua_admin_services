package com.cua.admin.model.core.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor

public enum AircraftStatus {
    
    ACTIVE("Activo"),
    INACTIVE("Inactivo"),
    MAINTENANCE("En mantenimiento"),
    OUT_OF_ORDER("Fuera de servico");
    
    private final String description;
}
