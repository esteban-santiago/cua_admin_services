package com.cua.admin.services.inventory;

import com.cua.admin.repositories.inventory.ProductRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    @Autowired //No es obligatorio
    private final ProductRepository productRepository;
} 
