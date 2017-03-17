package com.cua.admin.model.sales;

import com.cua.admin.model.operation.inventory.Product;
import lombok.Data;

@Data
public class SaleOrderItem {

    public SaleOrderItem(Integer quantity, Float price) {
        this.quantity = quantity;
        this.price = price;
    }
    
    private Integer id;
    private Product product;
    private Integer quantity;
    private Float price;
}
