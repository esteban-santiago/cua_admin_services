package com.cua.admin.controllers.rest.accounting;

import com.cua.admin.services.accounting.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class AccountingRestController {
    @Autowired
    private final AccountService accountService;
}
