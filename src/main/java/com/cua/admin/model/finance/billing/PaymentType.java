package com.cua.admin.model.finance.billing;

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
    DEBIT_CARD("Tarjeta de Débito"),
    BANK_WIRE("Transferencia Bancaria");
    
    private final String description;    
}
