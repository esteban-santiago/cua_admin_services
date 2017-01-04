/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
