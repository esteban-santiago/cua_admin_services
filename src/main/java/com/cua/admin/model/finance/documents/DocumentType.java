package com.cua.admin.model.finance.documents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum DocumentType {

    CNI("Nota de Crédito Emitida"), //Credit Note Issued
    FRI("Ficha de Vuelo Emitida"),  //Flight Record Issued
    RCI("Recibo Emitido"), //Receipt Issued
    MFI("Cuota Social Emitida"), //Membership Fee Issued
    DNI("Nota de Débito"),  //Debit Note Issued
    @Deprecated //No implementado
    POI("Orden de Pago Emitida"),  //Pay Order Issued
    @Deprecated //No implementado
    CPI("Pago de Contrato Emitido"),  //Contract Payment Issued
    @Deprecated //No implementado
    SOI("Pedido de venta Emitido"); //Sale Order Issued
    
    private final String description;
    
    public static Set<DocumentType> getCompensators() {
        Set<DocumentType> compensators = new HashSet<>();
        compensators.add(RCI);
        compensators.add(CNI);
        return compensators;
    }

    public static Set<DocumentType> getCompensables() {
        Set<DocumentType> compensables = new HashSet<>();
        compensables.add(FRI);
        compensables.add(DNI);
        return compensables;        
    }

    
}
