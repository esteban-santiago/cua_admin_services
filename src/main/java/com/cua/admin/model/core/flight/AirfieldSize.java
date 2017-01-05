package com.cua.admin.model.core.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esteban_santiago
 */

@Getter
@RequiredArgsConstructor
public enum AirfieldSize {
    SMALL("Pequeño"),
    MEDIUM("Mediano"),
    LARGE("Grande");
    
    private final String description;
    
}
