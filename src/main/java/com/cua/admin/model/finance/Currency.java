package com.cua.admin.model.finance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor

public enum Currency {
    
    ARS("Pesos Argentinos"),
    USD("Dólares Estadounidenses"),
    BRL("Reales Brasileros"),
    CLP("Peso Chileno"),
    EUR("Euro");

    private final String description;

}
