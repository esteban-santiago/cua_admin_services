package com.cua.admin.model.sales.exceptions;

import com.cua.admin.model.core.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(Integer id) {
        super("El cliente " + id + " no existe.");
    }
    
}
