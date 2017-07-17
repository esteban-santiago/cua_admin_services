package com.cua.admin.services.operation.inventory;

import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.model.operation.inventory.ProductGroup;
import com.cua.admin.model.operation.inventory.ProductSubGroup;
import com.cua.admin.model.operation.inventory.ProductType;
import com.cua.admin.repositories.operation.inventory.ProductRepository;
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
    
    /*
    ** Product Basic Services
    */
    public Product get(Integer id) {
        return this.productRepository.findById(id).get();
    }
    
    public void save(Product product) {
        this.productRepository.save(product);
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
