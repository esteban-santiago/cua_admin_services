package com.cua.admin.test.model.accounting;
import com.cua.admin.model.accounting.AccountingEntry;
import com.cua.admin.model.accounting.AccountingEntryItem;
import com.cua.admin.model.accounting.AccountingEntryItemType;
import com.cua.admin.model.accounting.Currency;
import com.cua.admin.model.accounting.documents.CreditNoteIssued;
import com.cua.admin.model.accounting.documents.Document;
import com.cua.admin.model.accounting.documents.DocumentType;
import com.cua.admin.model.accounting.documents.FlightRecordIssued;
import com.cua.admin.model.accounting.documents.ReceiptIssued;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import com.cua.admin.model.billing.PaymentMethod;
import com.cua.admin.repositories.accounting.AccountRepository;
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


//@DataJpaTest
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
        TemplateEntry entry = templateEntryRepository.findByDocumentType(DocumentType.FRI);
        System.out.println(entry);
        PaymentMethod paymentMethod = paymentMethodRepository.findById(1);
        //System.out.println(entry.getEntryLineByPaymentMethod(paymentMethod));
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
        rci.setCompensationDocument(rci.getId());
        rci.setCompensationDate(LocalDate.now());
        rci.setPaymentMethod(paymentMethodRepository.findById(1)); //Cash
        documentRepository.saveAndFlush(rci);
        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(9000);

        //Compensando Ficha de vuelo
        fve.setCompensationDate(LocalDate.now());
        fve.setCompensationDocument(rci.getId());
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
