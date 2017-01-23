package com.cua.admin.model.accounting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor

public enum Currency {
    
    ARS("Pesos Argentinos"),
    USD("Dólares Estadounidenses"),
    BRL("Reales Brasileros"),
    EUR("Euro");

    private final String description;

}
