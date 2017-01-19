package com.cua.admin.model.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends Exception {

    public MemberNotFoundException(Integer id) {
        super("El socio " + id + " no existe.");
    }
    
}
