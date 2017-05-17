package com.cua.admin.services.finance;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.repositories.operation.flight.AircraftRepository;
import com.cua.admin.services.accounting.AccountingEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
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

    @Autowired
    private AircraftRepository aircraftRepository;

    public void save(FlightRecord flightRecord) throws Throwable {
        FlightRecordIssued flightRecordIssued = new FlightRecordIssued();
        flightRecordIssued.setReferencedDocumentId(flightRecord.getId());
        Payment payment = new Payment();

        Aircraft aircraft = aircraftRepository.findOne(Example.of(flightRecord.getAircraft()));
        payment.setAmount(flightRecord.getAmountOfHours() * aircraft.getProductProfile().getProduct().getPrice());
        payment.setCurrency(aircraft.getProductProfile().getProduct().getCurrency());

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

    //public <T extends Document> T compensate(T document) {
    //    return compensate(document, document.getCompensatedDocuments());
    //}

    //private <T extends Document> T compensate(T parent, List<? extends Document> children) {
    public <T extends Document> T compensate(T parent) {
        documentService.save(parent);

        parent.setCompensatedBy(parent);
        parent.setCompensationDate(LocalDate.now());
        parent.compensate();

        
        //T savedParent = 
        //documentService.save(parent);
        
        parent.getCompensatedDocuments().forEach(
                child -> {
                    child.setCompensationDate(LocalDate.now());
                    child.setCompensatedBy(parent);
                    child.compensate();
                    documentService.save(child);
                });

        return documentService.save(parent);
    }

    /*
    private <T extends Document> void compensate(T parent, T child) {
        child.setCompensationDate(LocalDate.now());
        child.setCompensatedBy(parent);
        child.compensate();
        documentService.save(child);
        if (!parent.getCompensatedDocuments().contains(child)) 
            parent.getCompensatedDocuments().add((T extends Document)child);    
    }*/
    
    public Float balance(Person person) {
        return (float) documentService.getAllByPerson(person.getId()).stream()
                .mapToDouble(Document::getAmount)
                .sum();
    }

}
