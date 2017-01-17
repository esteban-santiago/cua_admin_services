package com.cua.admin.repositories.inventory;

import com.cua.admin.model.inventory.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductSubGroupRepository extends JpaRepository<ProductSubGroup, Integer> {

    ProductSubGroup findById(Integer id);
}
