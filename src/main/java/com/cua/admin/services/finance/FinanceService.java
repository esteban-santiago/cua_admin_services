package com.cua.admin.services.finance;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.repositories.operation.flight.AircraftRepository;
import com.cua.admin.services.accounting.AccountingEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

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
        Payment debt = new Payment();

        
        //Revisar esto: para que lo vuelvo a traer de la base?
        //Aircraft aircraft = aircraftRepository.findOne(Example.of(flightRecord.getAircraft()));
        debt.setAmount(flightRecord.getAmountOfHours() * flightRecord.getAircraft().getProductProfile().getProduct().getPrice());
        debt.setCurrency(flightRecord.getAircraft().getProductProfile().getProduct().getCurrency());

        flightRecordIssued.getPayments().add(debt);

        flightRecordIssued.setPerson(flightRecord.getCrew().stream()
                .filter(member -> member.getCrewMemberRole().equals(CrewMemberRole.PIC))
                .findAny().get().getPerson());
        flightRecordIssued.open();
        save(flightRecordIssued);
    }

    public <T extends Document> T save(T document) throws Throwable {
        if (isCompensable(document)) {
            compensate(document);
        }
        return saveAndCreateEntry(document);
    }

    /*
        El valor del documento compensador debe ser igual al total de los documentos
        compensados para poder cerrar el documento.
     */
    public <T extends Document> Boolean isCompensable(T document) throws Throwable{
        try {
            return document.getDocumentBalanceAmount() == 0;
        } catch (NullPointerException npe) {
            return false;
        }
    }

    private <T extends Document> T saveAndCreateEntry(T document) throws Throwable {
        //Graba el documento
        document = documentService.save(document);
        
        // Lo saco hasta tener el modelo Ok
        //Contabiliza el documento (crea el asiento)
        //Tira un NullPointerException en la compensación
        //accountingEntryService.saveAccountingEntryUsingTemplate(document);

        return document;

    }

    //public <T extends Document> T compensate(T document) {
    //    return compensate(document, document.getCompensatedDocuments());
    //}
    //private <T extends Document> T compensate(T parent, List<? extends Document> children) {
    public <T extends Document> T compensate(T parent) {
        documentService.save(parent);

        //Si compenso con el mismo documento me lo trae como una linea en los compensados
        parent.setCompensatedBy(parent);
        parent.setCompensationDate(LocalDate.now());
        parent.compensate();

        parent.getCompensatedDocuments().forEach(
                child -> {
                    child.setCompensationDate(LocalDate.now());
                    child.setCompensatedBy(parent);
                    child.compensate();
                    documentService.save(child);
                });
        //return documentService.save(parent);
        return parent;
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
