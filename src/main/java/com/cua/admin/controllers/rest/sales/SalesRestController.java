package com.cua.admin.controllers.rest.sales;

import com.cua.admin.model.core.Person;
import com.cua.admin.services.sales.SalesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class SalesRestController {
    @Autowired
    private final SalesService salesService;
  
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Person> getCustomer(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) throws Throwable {
        return new ResponseEntity<>(this.salesService.getCustomer(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Person>> getEmployees() {
        return new ResponseEntity<>(salesService.getCustomers(), HttpStatus.OK);
    }
}
