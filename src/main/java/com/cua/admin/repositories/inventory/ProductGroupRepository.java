package com.cua.admin.repositories.inventory;

import com.cua.admin.model.inventory.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {

    ProductGroup findById(Integer id);
    Set<Product>  findBySubGroups(ProductSubGroup subGroup);
}
