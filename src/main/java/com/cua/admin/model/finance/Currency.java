package com.cua.admin.model.finance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor

public enum Currency {
    
    ARS("Pesos Argentinos"),
    BRL("Reales Brasileros"),
    CLP("Peso Chileno"),
    CUA("Peso CUA"),
    EUR("Euro"),
    USD("Dólares Estadounidenses");

    private final String description;

}
