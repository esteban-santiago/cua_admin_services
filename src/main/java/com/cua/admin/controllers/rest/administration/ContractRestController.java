package com.cua.admin.controllers.rest.administration;

import com.cua.admin.model.administration.Contract;
import com.cua.admin.model.core.Person;
import com.cua.admin.model.core.exceptions.PersonNotFoundException;
import com.cua.admin.services.accounting.AccountService;
import com.cua.admin.services.administration.ContractService;
import com.cua.admin.services.core.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/administration")
@RequiredArgsConstructor
public class ContractRestController {

    @Autowired
    private final ContractService contractService;
    
    @Autowired
    private final PersonService personService;

    @RequestMapping(value = "/contract", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Contract>> get() {
        return ResponseEntity.ok(contractService.getAll());
    }

    @RequestMapping(value = "/contract/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Contract> getById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(contractService.get(id));
    }

    @RequestMapping(value = "/contract/person/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Contract>> getByPersonId(@PathVariable("id") Person person) throws Throwable {
        return ResponseEntity.ok(contractService.getByPerson(personService.get(person)));
    }

}
