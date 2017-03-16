/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.tests.model.commercial;

import com.cua.admin.model.commercial.Item;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author esantiago
 */
public class Document {
    
    public Document() {
    }
    
    @Test
    public void SummarizeItems() {
        List<Item> items = new ArrayList<>();
        
        items.add(new Item(2, 2.1F));
        items.add(new Item(2, 2.2F));
        items.add(new Item(12, 22.5F));

        
        System.out.println(
        
                items.stream().mapToDouble((item) -> item.getQuantity() * item.getPrice()).sum()
         
        
        );
    
    }
}
