package com.cua.admin.tests.model.finance;

import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.CreditNoteIssued;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.repositories.finance.billing.PaymentMethodRepository;
import com.cua.admin.repositories.finance.billing.PaymentTermRepository;
import com.cua.admin.services.accounting.AccountingEntryService;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.finance.DocumentService;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FinanceDocumentTests extends SpringIntegrationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private DocumentService documentService;

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

    @Test
    public void save() throws Throwable {
        ReceiptIssued rci = new ReceiptIssued();
        Payment cash = new Payment();
        cash.setMethod(paymentMethodRepository.findById(1).get());
        cash.setCurrency(Currency.ARS);
        cash.setAmount(4632F);

        rci.getPayments().add(cash);

        rci.setPerson(personService.getMember(100));

        List<Document> compensated = documentService.getAllCompensables();
        
        rci.setCompensatedDocuments(compensated);
        documentService.save(rci);

        assertThat(documentService.get(rci.getId()).getCompensatedDocuments().size()).isGreaterThan(0);
    }

    @Test
    public void compensateWithCash() throws Throwable {

        ReceiptIssued rci = new ReceiptIssued();
        Payment cash = new Payment();
        cash.setMethod(paymentMethodRepository.findById(1).get());
        cash.setCurrency(Currency.ARS);
        cash.setAmount(4632F);

        rci.getPayments().add(cash);

        rci.setPerson(personService.getMember(100));

        
        rci.setCompensatedDocuments(documentService.getAllCompensables());
        
        //financeService.compensate(rci);

        financeService.save(rci);
        
        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //Si está compensado el valor del documento padre es igual a la sumatoria de los hijos
        //assertThat(((float) rci.getCompensatedDocuments().stream()
        //    .mapToDouble(Document::getAmount).sum()) + rci.getTotalAmount())
        //        .isEqualByComparingTo(0F);
        //assertThat(rci.getDocumentBalanceTotalAmount()).isEqualByComparingTo(0F);
        assertThat( (rci.getDocumentBalanceTotalAmount() == 0) && rci.isCompensated() ).isTrue();

    }

   @Test
    public void compensateWithDebitCard() throws Throwable {

        ReceiptIssued rci = new ReceiptIssued();
        Payment debit = new Payment();
        debit.setMethod(paymentMethodRepository.findById(5).get()); //Pago con Tarjeta de Débito
        debit.setCurrency(Currency.ARS);
        debit.setAmount(4632F);

        rci.getPayments().add(debit);

        rci.setPerson(personService.getMember(100));

        rci.setCompensatedDocuments(documentService.getAllCompensables());
        
        //financeService.compensate(rci);

        financeService.save(rci);

        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //Si está compensado el valor del documento compensador es igual a la sumatoria de los compensados
        //assertThat(((float) rci.getCompensatedDocuments().stream()
        //    .mapToDouble(Document::getAmount).sum()) + rci.getTotalAmount())
        //        .isEqualByComparingTo(0F);

        assertThat( (rci.getDocumentBalanceTotalAmount() == 0) && rci.isCompensated() ).isTrue();

    }

    @Test
    public void compensateWithBankCheck() throws Throwable {

        ReceiptIssued rci = new ReceiptIssued();
        Payment bank_check = new Payment();
        bank_check.setMethod(paymentMethodRepository.findById(2).get()); //Cheque Bancario
        bank_check.setTerm(paymentTermRepository.findById(6)); //Cheque de más de 30 dias
        bank_check.setCurrency(Currency.ARS);

        rci.getPayments().add(bank_check);

        rci.setPerson(personService.getMember(100));

        rci.setCompensatedDocuments(documentService.getAllCompensables());
                
        Float amount = (((float) rci.getCompensatedDocuments().stream()
            .mapToDouble(Document::getAmount).sum()));

        bank_check.setAmount(amount);

        bank_check.setCharge(bank_check.getAmount() * bank_check.getTerm().getCharge());

        //financeService.compensate(rci);

        financeService.save(rci);

        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //Si está compensado el valor del documento padre es igual a la sumatoria de los hijos
        //assertThat((rci.getAmount()) +
        //        ((float) rci.getCompensatedDocuments().stream()
        //            .mapToDouble(Document::getAmount).sum()))
        //        .isEqualByComparingTo(0F);

        assertThat( (rci.getDocumentBalanceTotalAmount() == 0) && rci.isCompensated() ).isTrue();
    }

    @Test
    public void compensateWithCreditCard() throws Throwable {

        ReceiptIssued rci = new ReceiptIssued();
        Payment credit = new Payment();
        credit.setMethod(paymentMethodRepository.findById(4).get()); //Tarjeta de Crédito
        credit.setTerm(paymentTermRepository.findById(3)); //Pago en una cuota
        credit.setCurrency(Currency.ARS);

        rci.getPayments().add(credit);

        rci.setPerson(personService.getMember(100));

        rci.setCompensatedDocuments(documentService.getAllCompensables());
                
        Float amount = (((float) rci.getCompensatedDocuments().stream()
            .mapToDouble(Document::getAmount).sum()));

        credit.setAmount(amount);

        credit.setCharge(credit.getAmount() * credit.getTerm().getCharge());

        //financeService.compensate(rci);

        financeService.save(rci);

        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //Si está compensado el valor del documento padre es igual a la sumatoria de los hijos
        //assertThat((rci.getAmount()) +
        //        ((float) rci.getCompensatedDocuments().stream()
        //            .mapToDouble(Document::getAmount).sum()))
        //        .isEqualByComparingTo(0F);
        
        System.out.println("----amount---");
        System.out.println(rci.getDocumentBalanceTotalAmount());

        System.out.println("----compensated---");
        System.out.println(rci.isCompensated());
        
        assertThat( (rci.getDocumentBalanceTotalAmount() == 0) && rci.isCompensated() ).isTrue();

    }

    @Test
    public void compensateWithBankCheckAndCreditCard() throws Throwable {

        ReceiptIssued rci = new ReceiptIssued();
        //Creo una instancia de Pago con un Cheque
        Payment bank_check = new Payment();
        bank_check.setMethod(paymentMethodRepository.findById(2).get()); //Cheque Bancario
        bank_check.setTerm(paymentTermRepository.findById(6)); //Cheque de más de 30 dias
        bank_check.setCurrency(Currency.ARS);

        //Creo una instancia de Pago con tarjeta de crédito
        Payment credit = new Payment();
        credit.setMethod(paymentMethodRepository.findById(4).get()); //Tarjeta de Crédito
        credit.setTerm(paymentTermRepository.findById(3)); //Pago en una cuota
        credit.setCurrency(Currency.ARS);


        rci.setPerson(personService.getMember(100));

        rci.setCompensatedDocuments(documentService.getAllCompensables());
        
        Float amount = (((float) rci.getCompensatedDocuments().stream()
            .mapToDouble(Document::getAmount).sum()));

        bank_check.setAmount((float)(0.5 * amount));

        bank_check.setCharge(bank_check.getAmount() * bank_check.getTerm().getCharge());

        credit.setAmount((float)(0.5 * amount));

        credit.setCharge(credit.getAmount() * credit.getTerm().getCharge());


        rci.getPayments().add(bank_check);
        rci.getPayments().add(credit);
        
        //financeService.compensate(rci);

        financeService.save(rci);

        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //Si está compensado el valor del documento padre es igual a la sumatoria de los hijos
        //assertThat((rci.getAmount()) +
        //        ((float) rci.getCompensatedDocuments().stream()
        //            .mapToDouble(Document::getAmount).sum()))
        //        .isEqualByComparingTo(0F);
        assertThat( (rci.getDocumentBalanceTotalAmount() == 0) && rci.isCompensated() ).isTrue();

    }

    @After
    public void list() {
        System.out.println("--------Documentos Abiertos---------");
        documentService.getAllOpened().forEach(System.out::println);

        System.out.println("--------Documentos Compensados---------");
        documentService.getAllCompensated().forEach(System.out::println);

        System.out.println("--------Documentos Compensables---------");
        documentService.getAllCompensables().forEach(System.out::println);

        System.out.println("--------Cuenta Corriente---------");
        documentService.getAllByPerson(100).forEach((document) -> {
            System.out.println(document.getPerson().getName()
                    + " | " + document.getLegalId()
                    + " : " + document.getDocumentType() + " : "
                    + " Sin cargos --> " + document.getAmount()
                    + " Total --> " + document.getTotalAmount());
        });

        System.out.println("Total: " + (float)
                documentService.getAllByPerson(100).stream()
                    .mapToDouble(Document::getAmount).sum()
                );

        System.out.println("--------Asientos---------");
        accountingEntryService.getAll().forEach(System.out::println);

    }
}
