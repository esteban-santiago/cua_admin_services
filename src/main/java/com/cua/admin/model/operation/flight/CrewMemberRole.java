package com.cua.admin.model.operation.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
/**
 *
 * @author esantiago
 */

@Getter
@RequiredArgsConstructor
public enum CrewMemberRole {
    PIC("Piloto al comando"),
    CIC("Copiloto al comando"),
    INST("Instructor"),
    PASS("Pasajero"),
    INSP("Inspector");
    
    private final String description;
    
}
