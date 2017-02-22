package com.cua.admin.repositories.operation.inventory;

import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import com.cua.admin.model.operation.inventory.ProductType;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(Integer id);
    Set<Product> findByGroup(ProductGroup group);
    Set<Product> findBySubGroup(ProductSubGroup subGroup);
    Set<Product> findByType(ProductType productType);
}
