package com.cua.admin.controllers.rest.administration;

import com.cua.admin.model.administration.Contract;
import com.cua.admin.services.accounting.AccountService;
import com.cua.admin.services.administration.ContractService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/administration")
@RequiredArgsConstructor
public class ContractRestController {

    @Autowired
    private final ContractService contractService;

    @RequestMapping(value = "/contract", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Contract>> get() {
        return ResponseEntity.ok(contractService.getAll());
    }
}
