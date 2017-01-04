package com.cua.admin.model.billing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum PaymentMethod {
    CASH("Efectivo"),
    CREDIT_CARD("Tarjeta de Crédito"), 
    DEBIT_CARD("Tarjeta de Débito"), 
    CHECK("Cheque"),
    MONEY_ACCOUNT("Cuenta Corriente $"),
    HOURS_ACCOUNT("Cuenta Corriente Hs");
    
    private final String description;
}
