package com.cua.admin.tests.entities;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.repositories.accounting.AccountRepository;
import java.util.function.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingTests {
    @Autowired
    private AccountRepository accountRepository;
    
    //@Test
    public void getAccounts() {
        for(Account account : accountRepository.findAll()) {
            System.out.println(account.getDescription());
        }
    }
    
    @Test
    public void getMajorAccounts() {
        System.out.println("----------------Cuentas Mayores--------------");
        accountRepository.findBySecondOrderGrouper(0).stream().forEach((Account account) -> {
            System.out.println(account.toFormattedString() + " - " + account.getDescription());
        });
    }

    
}
