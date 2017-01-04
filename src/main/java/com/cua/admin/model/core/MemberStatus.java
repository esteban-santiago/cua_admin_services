
package com.cua.admin.model.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    ACTIVE("Activo"),
    INACTIVE("Baja"),
    LICENSED("Licencia");
    
    private final String description;
}
