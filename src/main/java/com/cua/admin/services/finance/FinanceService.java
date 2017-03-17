package com.cua.admin.services.finance;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.services.accounting.AccountingEntryService;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        flightRecordIssued.setAmount(flightRecord.getAmountOfHours() * flightRecord.getAircraft().getProductProfile().getProduct().getPrice());
        flightRecordIssued.setCurrency(flightRecord.getAircraft().getProductProfile().getProduct().getCurrency());
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
        //Contabiliza el documento (crea el asiento)
        accountingEntryService.saveAccountingEntryUsingTemplate(document);
    }
    
    public <T extends Document> void compensate(T parent, List<T> childs) {
        childs.forEach(child -> compensate(parent, child));
    }

    public <T extends Document> void compensate(T parent, T child) {
        parent.setCompensationDocument(parent);
        parent.setCompensationDate(LocalDate.now());
        parent.compensate();
        documentService.save(parent);
        child.setCompensationDate(LocalDate.now());
        child.setCompensationDocument(parent);
        child.compensate();
        documentService.save(child);
    }

}
