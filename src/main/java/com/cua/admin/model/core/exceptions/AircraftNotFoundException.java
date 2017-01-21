package com.cua.admin.model.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AircraftNotFoundException extends Exception {

    public AircraftNotFoundException(Integer id) {
        super("La aeronave " + id + " no existe.");
    }
    
}
