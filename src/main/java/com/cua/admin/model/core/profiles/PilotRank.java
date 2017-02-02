package com.cua.admin.model.core.profiles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor
public enum PilotRank {
    STUDENT("Alumno"),
    PILOT("Piloto al Mando"),
    INSTRUCTOR("Instructor"),
    INSPECTOR("Inspector");

private final String description;
    
}
