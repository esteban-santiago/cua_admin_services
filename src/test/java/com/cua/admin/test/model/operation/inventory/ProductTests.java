package com.cua.admin.test.model.operation.inventory;

import com.cua.admin.repositories.operation.inventory.ProductGroupRepository;
import com.cua.admin.repositories.operation.inventory.ProductRepository;
import com.cua.admin.repositories.operation.inventory.ProductSubGroupRepository;
import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import com.cua.admin.model.operation.inventory.ProductType;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import java.util.HashSet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ProductTests extends SpringIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductGroupRepository productGroupRepository;
    
    @Autowired
    private ProductSubGroupRepository productSubGroupRepository;

    @Test
    public void createProduct(){
        Product product = new Product();
        ProductGroup group = new ProductGroup();
        ProductSubGroup subGroup = new ProductSubGroup();
        group.setSubGroups(new HashSet<>());
        group.getSubGroups().add(subGroup);
        group.setDescription("Material didáctico");
        subGroup.setDescription("Manual");
        product.setGroup(group);
        product.setSubGroup(subGroup);        
        product.setDescription("Manual PA-11");
        product.setType(ProductType.PRODUCT);

        //productSubGroupRepository.save(product.getSubGroup());
        productGroupRepository.save(product.getGroup());
        productRepository.save(product);
        
        System.out.println(product);
        System.out.println("---------------");
        productRepository.findByType(ProductType.SERVICE).stream()
                .forEach(lproduct -> 
                        System.out.println(lproduct.getDescription()));
    }
}
