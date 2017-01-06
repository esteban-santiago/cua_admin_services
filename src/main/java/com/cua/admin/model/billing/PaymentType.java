/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.billing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esantiago
 */

@Getter
@RequiredArgsConstructor
public enum PaymentType {
    
    CASH("Efectivo"),
    BANK_CHECK("Cheque Bancario"),
    ACCOUNT("Cuenta Corriente"),
    CREDIT_CARD("Tarjeta de Crédito"),
    DEBIT_CARD("Tarjeta de Débito");

    private final String description;
}
