package com.cua.admin.model.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PilotNotFoundException extends Exception {

    public PilotNotFoundException(Integer id) {
        super("El piloto " + id + " no existe.");
    }
    
}
