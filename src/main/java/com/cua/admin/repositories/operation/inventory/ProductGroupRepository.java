package com.cua.admin.repositories.operation.inventory;

import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {

    ProductGroup findById(Integer id);
    Set<Product>  findBySubGroups(ProductSubGroup subGroup);
}
