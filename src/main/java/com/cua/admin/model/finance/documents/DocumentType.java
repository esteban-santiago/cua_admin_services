package com.cua.admin.model.finance.documents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentType {

    CNI("Nota de Crédito Emitida"), //Credit Note Issued
    FRI("Ficha de Vuelo Emitida"),  //Flight Record Issued
    RCI("Recibo Emitido"), //Receipt Issued
    @Deprecated
    DNI("Nota de Débito"),  //Debit Note Issued
    @Deprecated
    POI("Orden de Pago Emitida"),  //Pay Order Issued
    @Deprecated
    MFI("Cuota Social Emitida"), //Membership Fee Issued
    @Deprecated //No implementado
    SOI("Pedido de venta Emitido"); //Sale Order Issued
    
    private final String description;
    
}
