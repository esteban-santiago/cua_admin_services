package com.cua.admin.tests.core;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.AccountingEntry;
import com.cua.admin.model.accounting.AccountingEntryItem;
import com.cua.admin.model.accounting.Currency;
import com.cua.admin.model.accounting.documents.CreditNoteIssued;
import com.cua.admin.model.accounting.documents.Document;
import com.cua.admin.model.accounting.documents.FlightRecordIssued;
import com.cua.admin.model.core.repositories.UserRepository;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.documents.DocumentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
public class AccountingTests extends SpringIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;
  
    @Autowired
    private DocumentRepository<Document> documentRepository;

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
    public void createDocuments() {
        FlightRecordIssued fve = new FlightRecordIssued();
        fve.setAccountabilityAmount(3544F);
        fve.setAmount(3544F);
        fve.setCurrency(Currency.ARS);
        documentRepository.save(fve);
        entityManager.flush();
        System.out.println(fve);
        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(9000);
   
        CreditNoteIssued nce = new CreditNoteIssued();
        nce.setAccountabilityAmount(1544F);
        nce.setAmount(1544F);
        nce.setCurrency(Currency.ARS);
        documentRepository.save(nce);
        entityManager.flush();
        System.out.println(nce);
        assertThat(nce.getId()).isGreaterThan(0);
        assertThat(nce.getLegalId()).isGreaterThanOrEqualTo(8000);
   
        System.out.println("--------Documentos---------");
        for(Document document : documentRepository.findAll()){
            System.out.println(document.getDocumentType().getDescription() + " : " + document.getLegalId());
        }
    
    }
    
    @Test
    public void createAccountingEntry() {
        AccountingEntry entry = new  AccountingEntry();
        entry.setCreationDate(LocalDateTime.now());
        entry.setDescription("Asiento de prueba");
        entry.setFiscalYear(LocalDate.now().getYear());
        entry.setUser(userRepository.findById(1002));
        accountingEntryRepository.save(entry);
        AccountingEntryItem item1 = new AccountingEntryItem();
        AccountingEntryItem item2 = new AccountingEntryItem();
        item1.setCreationDate(LocalDateTime.now());
        item1.setAccount(accountRepository.findById(1700)); //Cuotas a cobrar
        item1.setCredit(350.00F);
        item2.setCreationDate(LocalDateTime.now());
        item2.setAccount(accountRepository.findById(8800)); //Cuotas y servicios
        item2.setDebit(350.00F);
        entry.addEntryItem(item1);
        entry.addEntryItem(item2);
        accountingEntryRepository.save(entry);
    }

}
