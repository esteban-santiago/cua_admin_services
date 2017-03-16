package com.cua.admin.model.commercial;

import com.cua.admin.model.operation.inventory.Product;
import lombok.Data;

/**
 *
 * @author esantiago
 */
@Data
public class Item {

    public Item(Integer quantity, Float price) {
        this.quantity = quantity;
        this.price = price;
    }
    
    private Integer id;
    private Product product;
    private Integer quantity;
    private Float price;
}
