package com.cua.admin.model.operation.flight.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightRecordNotFoundException extends Exception {

    public FlightRecordNotFoundException(Integer id) {
        super("La ficha de vuelo " + id + " no existe.");
    }
    
}
