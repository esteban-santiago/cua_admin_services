/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.core.flight;

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
