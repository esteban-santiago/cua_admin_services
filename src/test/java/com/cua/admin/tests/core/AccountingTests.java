package com.cua.admin.tests.core;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.AccountingEntry;
import com.cua.admin.model.accounting.AccountingEntryItem;
import com.cua.admin.model.accounting.documents.CreditNoteIssuedDocument;
import com.cua.admin.model.accounting.documents.FlightRecordIssuedDocument;
import com.cua.admin.repositories.UserRepository;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.documents.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountingTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DocumentRepository<FlightRecordIssuedDocument> flightRecordRepository;

    @Autowired
    private DocumentRepository<CreditNoteIssuedDocument> creditNoteRepository;

    @Autowired
    private AccountingEntryRepository accountingEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;
    
    @Test
    public void getAccounts() {
        for (Account account : accountRepository.findAll()) {
            System.out.println(account.getDescription());
        }
    }

    @Test
    public void getMajorAccounts() {
        System.out.println("----------------Cuentas Mayores--------------");
        accountRepository.findBySecondOrderGrouper(0).forEach((Account account) -> {
            System.out.println(account.toFormattedString() + " - " + account.getDescription());
        });
    }
    
    @Test
    public void createCreditNoteIssued() {
        CreditNoteIssuedDocument nce = new CreditNoteIssuedDocument();
        nce.setAccountabilityAmount(1544F);
        nce.setAmount(1544F);
        nce = creditNoteRepository.save(nce);
        entityManager.flush();
        assertThat(nce.getId()).isGreaterThanOrEqualTo(8000);
        assertThat(nce.getCreditNoteSequence()).isGreaterThanOrEqualTo(8000);

        FlightRecordIssuedDocument fve = new FlightRecordIssuedDocument();
        fve.setAccountabilityAmount(3544F);
        fve.setAmount(3544F);
        fve = flightRecordRepository.save(fve);
        entityManager.flush();
        assertThat(fve.getId()).isGreaterThanOrEqualTo(9000);
        assertThat(fve.getFlightRecordSequence()).isGreaterThanOrEqualTo(9000);
    }

    @Test
    public void createAccountingEntry() {
        AccountingEntry entry = new  AccountingEntry();
        entry.setCreationDate(LocalDateTime.now());
        entry.setDescription("Asiento de prueba");
        entry.setFiscalYear(LocalDate.now().getYear());
        entry.setUser(userRepository.findById(1002));
        //accountingEntryRepository.save(entry);
        AccountingEntryItem item1 = new AccountingEntryItem();
        AccountingEntryItem item2 = new AccountingEntryItem();
        item1.setCreationDate(LocalDateTime.now());
        item1.setAccount(accountRepository.findById(1700)); //Cuotas a cobrar
        item1.setCredit(350.00f);
        item2.setCreationDate(LocalDateTime.now());
        item2.setAccount(accountRepository.findById(8800)); //Cuotas y servicios
        item2.setDebit(350.00f);
        entry.addEntryItem(item1);
        entry.addEntryItem(item2);
        accountingEntryRepository.save(entry);
    }

}
