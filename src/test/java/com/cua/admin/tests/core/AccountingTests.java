package com.cua.admin.tests.core;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.AccountingEntry;
import com.cua.admin.model.accounting.AccountingEntryItem;
import com.cua.admin.model.accounting.documents.*;
import com.cua.admin.repositories.UserRepository;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.documents.DocumentRepository;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private AccountingEntryRepository accountingEntryRepository;
    @Autowired
    private UserRepository userRepository;
    
    
    //@Test
    public void getAccounts() {
        for (Account account : accountRepository.findAll()) {
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
    
    @Test
    public void createCreditNoteIssued() {
        Document nce = new CreditNoteIssuedDocument();
        nce.setAccountabilityAmount(1544F);
        nce.setAmount(1544F);
        documentRepository.save(nce);

        Document fve = new FlightRecordIssuedDocument();
        fve.setAccountabilityAmount(3544F);
        fve.setAmount(3544F);
        documentRepository.save(fve);

    }
    
    
    @Test
    public void createAccountingEntry() {
        AccountingEntry entry = new  AccountingEntry();
        entry.setCreationDate(LocalDate.now());
        entry.setDescription("Asiento de prueba");
        entry.setFiscalYear(LocalDate.now().getYear());
        entry.setUser(userRepository.findById(1002));
        //accountingEntryRepository.save(entry);
        AccountingEntryItem item1 = new AccountingEntryItem();
        AccountingEntryItem item2 = new AccountingEntryItem();
        item1.setCreationDate(LocalDate.now());
        item1.setCreationTime(LocalTime.now());
        item1.setAccount(accountRepository.findById(1700)); //Cuotas a cobrar
        item1.setCredit(350.00f);
        item2.setCreationDate(LocalDate.now());
        item2.setCreationTime(LocalTime.now());
        item2.setAccount(accountRepository.findById(8800)); //Cuotas y servicios
        item2.setDebit(350.00f);
        entry.addEntryItem(item1);
        entry.addEntryItem(item2);
        accountingEntryRepository.save(entry);
    }

}
