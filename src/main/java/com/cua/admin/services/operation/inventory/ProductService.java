package com.cua.admin.services.operation.inventory;

import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import com.cua.admin.model.operation.inventory.ProductType;
import com.cua.admin.repositories.operation.inventory.ProductGroupRepository;
import com.cua.admin.repositories.operation.inventory.ProductRepository;
import com.cua.admin.repositories.operation.inventory.ProductSubGroupRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    
    @Autowired //No es obligatorio si es final
    private final ProductRepository productRepository;
    
    @Autowired //No es obligatorio si es final
    private final ProductGroupRepository productGroupRepository;
    
    @Autowired //No es obligatorio si es final
    private final ProductSubGroupRepository productSubGroupRepository;
    
    /*
    ** Product Basic Services
    */
    public Product get(Integer id) {
        return this.productRepository.findById(id).get();
    }
    
    public ProductGroup getGroup(Integer id) {
        return this.productGroupRepository.findById(id).get();
    }

    public ProductSubGroup getSubGroup(Integer id) {
        return this.productSubGroupRepository.findById(id).get();
    }    
    
    public void save(Product product) {
        this.productRepository.save(product);
    }

    public void saveGroup(ProductGroup group) {
        this.productGroupRepository.save(group);
    }

    public void saveSubGroup(ProductSubGroup subGroup) {
        this.productSubGroupRepository.save(subGroup);
    }
    
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }
    
    public List<Product> getByGroup(ProductGroup group) {
        return this.productRepository.findByGroup(group);
    }
    
    public List<Product> getBySubGroup(ProductSubGroup subGroup) {
        return this.productRepository.findBySubGroup(subGroup);
    }    

    public List<Product> getByType(ProductType type) {
        return this.productRepository.findByType(type);
    }    
} 
