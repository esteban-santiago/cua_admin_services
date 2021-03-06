package com.cua.admin.tests.model.accounting;

import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.model.accounting.entries.AccountingEntryItem;
import com.cua.admin.model.accounting.entries.AccountingEntryItemType;
import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.repositories.accounting.entry.TemplateEntryRepository;
import com.cua.admin.repositories.it.UserRepository;
import com.cua.admin.services.accounting.AccountService;
import com.cua.admin.services.accounting.AccountingEntryService;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.finance.DocumentService;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Before;

@Transactional
public class AccountingTests extends SpringIntegrationTest {

    @Autowired
    private AccountingEntryService accountingEntryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateEntryRepository templateEntryRepository;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private FinanceService financeService;

    private FlightRecordIssued fri;

    @Before
    public void createBasicData() throws Throwable {
        Payment account = new Payment();
        account.setCurrency(Currency.ARS);
        fri = new FlightRecordIssued();
        account.setAmount(3544F);
        fri.getPayments().add(account);
        fri.setPerson(personService.getMember(100));
        financeService.save(fri);
    }

    @Test
    public void getAllAccounts() {
        accountService.getAll().stream().forEach(
                account -> System.out.println(account.toFormattedStringWithDescription())
        );
    }

    @Test
    public void recordAccountingEntry() throws Throwable {
        accountingEntryService.saveAccountingEntryUsingTemplate(fri);
        System.out.println("---------------- Asiento automáticos 2 ----------");
        accountingEntryService.getAll().stream().forEach(entry -> System.out.println(entry));
        System.out.println("----------------...-----------------");
    }

    @Test
    public void getTemplateEntry() throws Throwable {
        //Contabilizar los documentos de Ficha de Vuelo
        documentService.getAll().forEach(RCIDocument
                -> accountingEntryService.saveAccountingEntryUsingTemplate(RCIDocument)
        );

        System.out.println("----------------Asiento automáticos----------");
        accountingEntryService.getAll().stream().forEach(entry -> System.out.println(entry));
        System.out.println("----------------...-----------------");

    }

    @Test
    public void createAccountingEntry() {
        AccountingEntry entry = new AccountingEntry();
        entry.setCreationDate(LocalDateTime.now());
        entry.setDescription("Asiento de prueba de Cuota");
        entry.setFiscalYear(LocalDate.now().getYear());
        entry.setUser(userRepository.findById(100).get());
        accountingEntryService.save(entry);
        AccountingEntryItem item1 = new AccountingEntryItem();
        AccountingEntryItem item2 = new AccountingEntryItem();

        item1.setAccount(accountService.get(1900)); //Cuotas a cobrar
        item1.setItemType(AccountingEntryItemType.CREDIT);
        item1.setAmount(350.00F);
        item1.setCurrency(Currency.ARS);

        item2.setAccount(accountService.get(9000)); //Cuotas y servicios
        item2.setItemType(AccountingEntryItemType.DEBIT);
        item2.setAmount(350.00F);
        item2.setCurrency(Currency.ARS);

        entry.addEntryItem(item1);
        entry.addEntryItem(item2);
        accountingEntryService.save(entry);

        System.out.println("--------Asientos---------");
        accountingEntryService.getAll().stream()
                .forEach((_entry) -> {
                    System.out.println(_entry);
                });
    }

}
