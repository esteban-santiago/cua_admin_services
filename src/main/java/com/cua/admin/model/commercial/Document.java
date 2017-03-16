/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.commercial;

import java.util.List;

/**
 *
 * @author esantiago
 */
public abstract class Document {

    private List<Item> items;
    
    public Float getTotalAmount() {
        return (float) items.stream().mapToDouble(
                (item) -> item.getQuantity() * item.getPrice())
                .sum();
    }
    
}
