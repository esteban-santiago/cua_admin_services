package com.cua.admin.test.model.accounting;
import com.cua.admin.model.accounting.entries.AccountingEntryItem;
import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.model.accounting.entries.AccountingEntryItemType;
import com.cua.admin.model.accounting.*;
import com.cua.admin.model.accounting.documents.*;
import com.cua.admin.model.accounting.entries.*;
import com.cua.admin.repositories.core.UserRepository;
import com.cua.admin.repositories.accounting.entry.TemplateEntryRepository;
import com.cua.admin.repositories.billing.PaymentMethodRepository;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import com.cua.admin.repositories.accounting.documents.AccountingDocumentRepository;
import com.cua.admin.services.accounting.AccountService;
import com.cua.admin.services.accounting.AccountingEntryService;
import com.cua.admin.services.accounting.DocumentService;


@Transactional
public class AccountingTests extends SpringIntegrationTest {

    //@Autowired
    //private AccountRepository accountRepository;
  
    /*
    * Le puse el tipo en el generico para que me tome el mismo en el retorno
    ***************/
    @Autowired
    private AccountingDocumentRepository<Document> documentRepository; 

    @Autowired
    private AccountingEntryService accountingEntryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
     
    @Autowired
    private TemplateEntryRepository templateEntryRepository;
    
    
    @Test
    public void getTemplateEntry() {
        Document document = documentRepository.findById(100L);
        
        documentService.saveAccountingEntryUsingTemplate(document);

        Document document2 = documentRepository.findById(101L);
        documentService.saveAccountingEntryUsingTemplate(document2);

        System.out.println("----------------Asiento-----------");
        accountingEntryService.getAll().stream().forEach(entry -> System.out.println(entry)); 
        System.out.println("----------------...-----------------");
                
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
        //rci.setCompensationDocumentId(rci.getId());
        rci.setCompensationDocument(rci);
        rci.setCompensationDate(LocalDate.now());
        rci.setPaymentMethod(paymentMethodRepository.findById(1)); //Cash
        rci.close();
        documentRepository.saveAndFlush(rci);
        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(9000);

        //Compensando Ficha de vuelo
        fve.setCompensationDate(LocalDate.now());
        //fve.setCompensationDocumentId(rci.getId());
        fve.setCompensationDocument(rci);
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
        accountingEntryService.save(_entry);
    }
    
    @Test
    public void createAccountingEntry() {
        AccountingEntry entry = new  AccountingEntry();
        entry.setCreationDate(LocalDateTime.now());
        entry.setDescription("Asiento de prueba de Cuota");
        entry.setFiscalYear(LocalDate.now().getYear());
        entry.setUser(userRepository.findById(1002));
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
