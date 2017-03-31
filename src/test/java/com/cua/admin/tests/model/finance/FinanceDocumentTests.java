package com.cua.admin.tests.model.finance;

import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.CreditNoteIssued;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.repositories.finance.billing.PaymentMethodRepository;
import com.cua.admin.repositories.finance.billing.PaymentTermRepository;
import com.cua.admin.repositories.finance.documents.DocumentRepository;
import com.cua.admin.services.accounting.AccountingEntryService;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.finance.DocumentService;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FinanceDocumentTests extends SpringIntegrationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository<Document> documentRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentTermRepository paymentTermRepository;

    @Autowired
    private AccountingEntryService accountingEntryService;

    @Before
    public void createBasicDocuments() throws Throwable {
        Payment account = new Payment();
        account.setCurrency(Currency.ARS);

        FlightRecordIssued fve = new FlightRecordIssued();
        account.setAmount(3544F);
        fve.getPayments().add(account);
        fve.setPerson(personService.getMember(100));
        financeService.save(fve);

        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(70000000);

        FlightRecordIssued fri = new FlightRecordIssued();
        account.setAmount(2544F);
        fri.getPayments().add(account);
        fri.setPerson(personService.getMember(100));
        financeService.save(fri);

        assertThat(fri.getId()).isGreaterThan(0);
        assertThat(fri.getLegalId()).isGreaterThanOrEqualTo(70000000);

        FlightRecordIssued fri2 = new FlightRecordIssued();
        account.setAmount(1544F);
        fri2.getPayments().add(account);
        fri2.setPerson(personService.getMember(100));
        financeService.save(fri2);

        assertThat(fri.getId()).isGreaterThan(0);
        assertThat(fri.getLegalId()).isGreaterThanOrEqualTo(70000000);

        CreditNoteIssued nce = new CreditNoteIssued();
        account.setAmount(1544F);
        nce.getPayments().add(account);
        nce.setPerson(personService.getMember(100));

        financeService.save(nce);

        assertThat(nce.getId()).isGreaterThan(0);
        assertThat(nce.getLegalId()).isGreaterThanOrEqualTo(8000);
        assertThat(nce.getAmount()).isLessThan(0);
    }

    //@Test
    public void compensateWithCash() throws Throwable {

        ReceiptIssued rci = new ReceiptIssued();
        Payment cash = new Payment();
        cash.setMethod(paymentMethodRepository.findById(1));
        cash.setCurrency(Currency.ARS);
        cash.setAmount(4632F);

        rci.getPayments().add(cash);

        rci.setPerson(personService.getMember(100));

        Set<Document> compensated = new HashSet();

        documentService.getAllCompensables().stream()
                .forEach((document) -> {
                    compensated.add(document);
                });

        financeService.compensate(rci, compensated);

        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //Si está compensado el valor del documento padre es igual a la sumatoria de los hijos
        assertThat(((float) rci.getCompensatedDocuments().stream().mapToDouble(
                (item) -> item.getAmount()).sum()) + rci.getTotalAmount())
                .isEqualByComparingTo(0F);

    }

    @Test
    public void compensateWithCreditCard() throws Throwable {

        ReceiptIssued rci = new ReceiptIssued();
        Payment credit = new Payment();
        credit.setMethod(paymentMethodRepository.findById(4)); //Tarjeta de Crédito
        credit.setTerm(paymentTermRepository.findById(3)); //Una cuota
        credit.setCurrency(Currency.ARS);

        rci.getPayments().add(credit);

        rci.setPerson(personService.getMember(100));

        Set<Document> compensated = new HashSet();

        documentService.getAllCompensables().stream().forEach((document) -> {
            compensated.add(document);
        });

        Float amount = (((float) compensated.stream().mapToDouble(
                (item) -> item.getAmount()).sum()));
        
        credit.setAmount(amount);        
        
        credit.setCharge(credit.getAmount() * credit.getTerm().getCharge());

        
        financeService.compensate(rci, compensated);

        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //Si está compensado el valor del documento padre es igual a la sumatoria de los hijos
        assertThat((rci.getAmount()) + 
                ((float) rci.getCompensatedDocuments().stream().mapToDouble(
                (item) -> item.getAmount()).sum()))
                .isEqualByComparingTo(0F);

    }

    @After
    public void list() {
        System.out.println("--------Documentos Abiertos---------");
        documentService.getAllOpened().stream().forEach((document) -> {
            System.out.println(document);
        });

        System.out.println("--------Documentos Compensados---------");
        documentService.getAllCompensated().stream().forEach((document) -> {
            System.out.println(document);
        });

        System.out.println("--------Documentos Compensables---------");
        documentService.getAllCompensables().stream().forEach((document) -> {
            System.out.println(document);
        });

        System.out.println("--------Cuenta Corriente---------");
        documentService.getAllByPerson(100).stream().forEach((document) -> {
            System.out.println(document.getPerson().getName()
                    + " | " + document.getLegalId()
                    + " : " + document.getDocumentType()
                    + " --> " + document.getAmount());
        });

        System.out.println("Total: " + (float)
                documentService.getAllByPerson(100).stream().mapToDouble(
                        (document) -> document.getAmount()  ).sum()
                );
        
        System.out.println("--------Asientos---------");
        accountingEntryService.getAll().stream().forEach((entry) -> {
            System.out.println(entry);
        });

    }
}
