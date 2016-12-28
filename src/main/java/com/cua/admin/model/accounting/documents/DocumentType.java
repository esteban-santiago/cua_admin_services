/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting.documents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentType {

    NCE("Nota de Crédito Emitida"),
    FVE("Ficha de Vuelo Emitida"), 
    OPE("Orden de Pago Emitida"), 
    CSE("Cuota Social Emitida"),
    RCE("Recibo Emitido");

    private final String description;
    
}
