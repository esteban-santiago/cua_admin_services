package com.cua.admin.model.accounting.entries.exceptions;

import com.cua.admin.model.finance.documents.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemplateEntryNotFoundException extends Exception {

    public TemplateEntryNotFoundException(Document document) {
        super("El Template de asiento para doumento " + document.getDocumentType() + " no existe.");
    }
    
}
