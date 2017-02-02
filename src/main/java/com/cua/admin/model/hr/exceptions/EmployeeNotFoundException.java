package com.cua.admin.model.hr.exceptions;

import com.cua.admin.model.core.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(Integer id) {
        super("La persona " + id + " no existe.");
    }
    
}
