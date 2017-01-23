package com.cua.admin.repositories.operation.inventory;

import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductType;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(Integer id);
    Set<Product> findByGroup(ProductGroup group);
    Set<Product> findBySubGroup(ProductSubGroup subGroup);
    Set<Product> findByType(ProductType productType);
}
