package com.cua.admin.controllers.rest.accounting;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.model.core.Person;
import com.cua.admin.services.accounting.AccountService;
import com.cua.admin.services.accounting.AccountingEntryService;
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
public class AccountingEntryRestController {

    @Autowired
    private final AccountingEntryService accountingEntryService;

    @RequestMapping(value = "/accounting_entry", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<AccountingEntry>> get() {
        return new ResponseEntity<>(accountingEntryService.getAll(), HttpStatus.OK);
    }
}
