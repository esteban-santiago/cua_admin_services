package com.cua.admin.tests.model.finance;

import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.PaymentMethod;
import com.cua.admin.model.finance.documents.CreditNoteIssued;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.repositories.finance.billing.PaymentMethodRepository;
import com.cua.admin.repositories.finance.documents.DocumentRepository;
import com.cua.admin.services.accounting.AccountingEntryService;
import com.cua.admin.services.finance.DocumentService;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class FinanceDocumentTests extends SpringIntegrationTest {

    @Autowired
    private FinanceService financeService; 
    
    @Autowired
    private DocumentService documentService; 

    @Autowired
    private DocumentRepository<Document> documentRepository;           
 
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;   
    
    @Autowired
    private AccountingEntryService accountingEntryService;
    
    @Test
    public void compensate() throws Throwable {
        FlightRecordIssued fve = new FlightRecordIssued();
        fve.setAmount(3544F);
        fve.setCurrency(Currency.ARS);
        financeService.save(fve);
        
        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(9000);

        ReceiptIssued rci = new ReceiptIssued();
        rci.setAmount(3544F);
        rci.setPaymentMethod(paymentMethodRepository.findById(1));
        rci.setCurrency(Currency.ARS);

        financeService.compensate(rci, fve);
        
        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(9000);
        financeService.save(rci);
        
        CreditNoteIssued nce = new CreditNoteIssued();
        nce.setAmount(1544F);
        nce.setCurrency(Currency.ARS);
        //nce.setPaymentMethod(paymentMethodRepository.findById(1));
        financeService.save(nce);
        assertThat(nce.getId()).isGreaterThan(0);
        assertThat(nce.getLegalId()).isGreaterThanOrEqualTo(8000);
        
        
        System.out.println("--------Documentos---------");
        documentService.getAll().stream().forEach((document) -> {
            System.out.println(document);
        });

        System.out.println("--------Asientos---------");
        accountingEntryService.getAll().stream().forEach((entry) -> {
            System.out.println(entry);
        });
        
        
    }

}
