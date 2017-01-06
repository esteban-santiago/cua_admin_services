package com.cua.admin.model.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor
public enum MemberRole {
    PUPIL("Alumno"),
    PILOT("Piloto"),
    INSTRUCTOR("Instructor"),
    INSPECTOR("Inspector");

private final String description;
    
}
