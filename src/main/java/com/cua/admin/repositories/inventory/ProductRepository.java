package com.cua.admin.repositories.inventory;

import com.cua.admin.model.inventory.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(Integer id);
    Set<Product>  findByGroup(ProductGroup group);
    Set<Product>  findByGroupSubGroup(ProductSubGroup subGroup);
}
