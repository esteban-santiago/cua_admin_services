package com.cua.admin.controllers.rest.operation.inventory;

import com.cua.admin.model.operation.inventory.Product;
import com.cua.admin.services.operation.inventory.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/operation/inventory")
@RequiredArgsConstructor
public class InventoryRestController {

    @Autowired
    private final ProductService productService;

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Product> get(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/product/group/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Product>> getByGroup(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.getByGroup(productService.getGroup(id)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/product/subgroup/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Product>> getBySubGroup(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.getBySubGroup(productService.getSubGroup(id)), HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/product/group/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Airfield>> get() {
        return new ResponseEntity<>(airfieldService.getAll(), HttpStatus.OK);
    } */       
    

}
