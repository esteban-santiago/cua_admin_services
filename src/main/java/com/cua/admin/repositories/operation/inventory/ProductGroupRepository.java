package com.cua.admin.repositories.operation.inventory;

import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {

    //ProductGroup findById(Integer id);
    List<Product>  findBySubGroups(ProductSubGroup subGroup);
}
