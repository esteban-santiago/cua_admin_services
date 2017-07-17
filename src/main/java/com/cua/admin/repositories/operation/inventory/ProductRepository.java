package com.cua.admin.repositories.operation.inventory;

import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import com.cua.admin.model.operation.inventory.ProductType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Product findById(Integer id);
    List<Product> findByGroup(ProductGroup group);
    List<Product> findBySubGroup(ProductSubGroup subGroup);
    List<Product> findByType(ProductType productType);
}
