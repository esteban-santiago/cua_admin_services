package com.cua.admin.model.core.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor
public enum Rating {
    CROSSING("Travesía"),
    PASSENGER("Transporte pasajeros"),
    NIGTH_FLIGHT("Nocturno local"),
    IFR("Vuelo instrumental");

    private final String description;
}
