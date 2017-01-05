package com.cua.admin.model.core.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esteban_santiago
 */
@Getter
@RequiredArgsConstructor
public enum ProductType {
    PRODUCT("Producto"),
    SERVICE("Servicio");
    
    private final String description;
    
}
