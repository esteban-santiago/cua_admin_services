package com.cua.admin.controllers.rest.finance;

import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.finance.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sapi/finance")
public class FinanceRestController {

    @Autowired
    private FinanceService financeService;
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Float> balance(@PathVariable("id") Integer id) throws Throwable {
        return ResponseEntity.ok(financeService.balance(personService.get(id)));
    }


    

}
