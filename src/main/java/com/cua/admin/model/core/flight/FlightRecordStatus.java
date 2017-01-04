package com.cua.admin.model.core.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor

public enum FlightRecordStatus {
    
    PENDING("Pendiente"),
    OPENED("En curso"),
    CLOSED("Concluida"),
    CANCELED("Anulada");
    
    private final String description;
}
