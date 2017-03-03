package com.cua.admin.tests.model.finance;

import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.documents.CreditNoteIssued;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.repositories.finance.billing.PaymentMethodRepository;
import com.cua.admin.repositories.finance.documents.DocumentRepository;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class FinanceDocumentTests extends SpringIntegrationTest {

    @Autowired
    private FinanceService financeService; 
    
    @Autowired
    private DocumentRepository<Document> documentRepository;           
 
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;   
    
    @Test
    public void compensate() throws Throwable {
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

}
