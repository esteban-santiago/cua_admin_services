package com.cua.admin.repositories.operation.inventory;

import com.cua.admin.model.operation.inventory.ProductSubGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductSubGroupRepository extends JpaRepository<ProductSubGroup, Integer> {

    ProductSubGroup findById(Integer id);
}
