package com.cua.admin.model.administration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContractNotFoundException extends Exception {

    public ContractNotFoundException(Long id) {
        super("El contrato " + id + " no existe.");
    }
    
}
