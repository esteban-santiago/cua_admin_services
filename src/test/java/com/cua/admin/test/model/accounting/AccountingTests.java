package com.cua.admin.test.model.accounting;
import com.cua.admin.model.accounting.*;
import com.cua.admin.model.accounting.documents.*;
import com.cua.admin.model.accounting.entries.*;
import com.cua.admin.repositories.accounting.*;
import com.cua.admin.repositories.core.UserRepository;
import com.cua.admin.repositories.accounting.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.documents.DocumentRepository;
import com.cua.admin.repositories.accounting.entry.TemplateEntryRepository;
import com.cua.admin.repositories.billing.PaymentMethodRepository;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
public class AccountingTests extends SpringIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;
  
    @Autowired
    private DocumentRepository<Document> documentRepository;

    @Autowired
    private AccountingEntryRepository accountingEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
     
    @Autowired
    private TemplateEntryRepository templateEntryRepository;
    
    @Test
    public void getTemplateEntry() {
        Document document = documentRepository.findById(100L);
        TemplateEntry entry = templateEntryRepository.findByDocumentType(document.getDocumentType());
        assertThat(entry.getId()).isGreaterThan(0);
        System.out.println("--------Asiento Template---------");
        System.out.println(entry);   
        System.out.println("----------------Asiento-----------");
        accountingEntryRepository.saveAndFlush(entry.getAccountingEntry(document));
        System.out.println(entry.getAccountingEntry(document));   
        System.out.println("---------------------------------");
        
        //AccountingEntry savedEntry = accountingEntryRepository.findById(1);
        //System.out.println(savedEntry);   
        System.out.println("---------------------------------");
        
        accountingEntryRepository.findAll().stream().forEach(entry_e -> System.out.println(entry_e));
        System.out.println("---------------------------------");
        
    }
    
    @Test
    public void createDocuments() {
        FlightRecordIssued fve = new FlightRecordIssued();
        fve.setAmount(3544F);
        fve.setCurrency(Currency.ARS);
        documentRepository.saveAndFlush(fve);
        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(9000);

        ReceiptIssued rci = new ReceiptIssued();
        rci.setAmount(3544F);
        rci.setCurrency(Currency.ARS);
        rci.setCompensationDocumentId(rci.getId());
        rci.setCompensationDate(LocalDate.now());
        rci.setPaymentMethod(paymentMethodRepository.findById(1)); //Cash
        rci.close();
        documentRepository.saveAndFlush(rci);
        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(9000);

        //Compensando Ficha de vuelo
        fve.setCompensationDate(LocalDate.now());
        fve.setCompensationDocumentId(rci.getId());
        fve.close();
        documentRepository.save(fve);
        
        CreditNoteIssued nce = new CreditNoteIssued();
        nce.setAmount(1544F);
        nce.setCurrency(Currency.ARS);
        nce.setPaymentMethod(paymentMethodRepository.findById(1));
        documentRepository.saveAndFlush(nce);
        assertThat(nce.getId()).isGreaterThan(0);
        assertThat(nce.getLegalId()).isGreaterThanOrEqualTo(8000);
   
        System.out.println("--------Documentos---------");
        documentRepository.findAll().stream().forEach((document) -> {
            System.out.println(document);
        });
    }
    
    //@Test
    public void createAutomaticAccountingEntry() {
        Document document = documentRepository.findById(100L);
        TemplateEntry entry = templateEntryRepository.findByDocumentType(document.getDocumentType());
        AccountingEntry _entry = entry.getAccountingEntry(document);
        accountingEntryRepository.save(_entry);
        System.out.println("---------Acá viene: -------\n" + _entry);
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
        //item1.setCreationDate(LocalDateTime.now());
        item1.setAccount(accountRepository.findById(1700)); //Cuotas a cobrar
        item1.setItemType(AccountingEntryItemType.CREDIT);
        item1.setAmount(350.00F);
        //item2.setCreationDate(LocalDateTime.now());
        item2.setAccount(accountRepository.findById(8800)); //Cuotas y servicios
        item2.setItemType(AccountingEntryItemType.DEBIT);
        item2.setAmount(350.00F);
        entry.addEntryItem(item1);
        entry.addEntryItem(item2);
        accountingEntryRepository.save(entry);
    }

}
