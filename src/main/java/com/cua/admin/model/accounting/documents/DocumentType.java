package com.cua.admin.model.accounting.documents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentType {

    CNI("Nota de Crédito Emitida"), //Credit Note Issued
    FRI("Ficha de Vuelo Emitida"),  //Flight Record Issued
    POI("Orden de Pago Emitida"),  //Pay Order Issued
    MFI("Cuota Social Emitida"), //Membership Fee Issued
    PAI("Pago Adelantado Emitid"), //Pay in Advance Issued
    RCI("Recibo Emitido"); //Receipt Issued

    private final String description;
    
}
