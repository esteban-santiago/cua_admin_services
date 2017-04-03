package com.cua.admin.controllers.rest.finance.billing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author esantiago
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentMethodNotFoundException extends Exception {

    public PaymentMethodNotFoundException(Integer id) {
        super("El método de pago " + id + " no existe.");
    }
    
}
