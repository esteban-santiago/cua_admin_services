package com.cua.admin.repositories.operation.inventory;

import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {

    //ProductGroup findOne(Integer id);
    Set<Product>  findBySubGroups(ProductSubGroup subGroup);
}
