package com.cua.admin.controllers.rest.accounting;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.services.accounting.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/accounting")
@RequiredArgsConstructor
public class AccountingRestController {

    @Autowired
    private final AccountService accountService;

    @RequestMapping(value = "/account", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Account>> get() {
        return ResponseEntity.ok(accountService.getAll());
    }
}
