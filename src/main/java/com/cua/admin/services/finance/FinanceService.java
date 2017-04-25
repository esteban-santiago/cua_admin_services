package com.cua.admin.services.finance;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.services.accounting.AccountingEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author esantiago
 */
@Service
@Transactional
@RequiredArgsConstructor
public class FinanceService {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AccountingEntryService accountingEntryService;

    public void save(FlightRecord flightRecord) throws Throwable {
        FlightRecordIssued flightRecordIssued = new FlightRecordIssued();
        flightRecordIssued.setReferencedDocumentId(flightRecord.getId());
        Payment payment = new Payment();

        payment.setAmount(flightRecord.getAmountOfHours() * flightRecord.getAircraft().getProductProfile().getProduct().getPrice());
        payment.setCurrency(flightRecord.getAircraft().getProductProfile().getProduct().getCurrency());

        flightRecordIssued.getPayments().add(payment);

        flightRecordIssued.setPerson(flightRecord.getCrew().stream()
                .filter(member -> member.getCrewMemberRole().equals(CrewMemberRole.PIC))
                .findAny().get().getPerson());
        flightRecordIssued.open();
        save(flightRecordIssued);
    }

    public <T extends Document> void save(T document) throws Throwable {
        saveAndCreateEntry(document);
    }

    private void saveAndCreateEntry(Document document) throws Throwable {
        //Graba el documento
        documentService.save(document);

        // Lo saco hasta tener el modelo Ok
        //Contabiliza el documento (crea el asiento)
        //accountingEntryService.saveAccountingEntryUsingTemplate(document);
    }

    public <T extends Document> T compensate(T document) {
        document.getCompensatedDocuments().forEach(child -> compensate(document, child));
        return documentService.save(document);
    }

    public <T extends Document> void compensate(T parent, Set<T> childs) {
        childs.forEach(child -> compensate(parent, child));
    }

    public <T extends Document> void compensate(T parent, T child) {
        parent.setCompensatedBy(parent);
        parent.setCompensationDate(LocalDate.now());
        parent.compensate();

        child.setCompensationDate(LocalDate.now());
        child.setCompensatedBy(parent);
        child.compensate();

        parent.getCompensatedDocuments().add(child);
    }

    public Float balance(Person person) {
        return (float) documentService.getAllByPerson(person.getId()).stream()
            .mapToDouble(Document::getAmount)
            .sum();
    }

}
