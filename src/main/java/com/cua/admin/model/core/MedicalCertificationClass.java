package com.cua.admin.model.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esteban_santiago
 */

@Getter
@RequiredArgsConstructor
public enum MedicalCertificationClass {
    CLASS_I("Clase 1"),
    CLASS_II("Clase 2"),
    CLASS_III("Clase 3");
    
    private final String description;
}
