package com.cua.admin.model.flight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */
@Getter
@RequiredArgsConstructor
public enum PilotRating {
    CROSSING("Travesía"),
    PASSENGER("Transporte Pasajeros"),
    NIGTH_FLIGHT("Nocturno Local"),
    IFR("Vuelo Instrumental");

    private final String description;
}
