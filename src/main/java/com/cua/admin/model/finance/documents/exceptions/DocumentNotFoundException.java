package com.cua.admin.model.finance.documents.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends Exception {

    public DocumentNotFoundException(Long id) {
        super("El documento " + id + " no existe.");
    }
    
}
