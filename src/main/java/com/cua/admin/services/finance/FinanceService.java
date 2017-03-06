package com.cua.admin.services.finance;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.repositories.finance.documents.DocumentRepository;
import com.cua.admin.services.accounting.AccountingEntryService;
import java.time.LocalDate;
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
    private DocumentRepository<Document> documentRepository;

    @Autowired
    private AccountingEntryService accountingEntryService;

    public void save(FlightRecord flightRecord) {
        FlightRecordIssued flightRecordIssued = new FlightRecordIssued();
        flightRecordIssued.setReferencedDocumentId(flightRecord.getId());
        flightRecordIssued.setAmount(flightRecord.getAmountOfHours() * flightRecord.getAircraft().getProductProfile().getProduct().getPrice());
        flightRecordIssued.setCurrency(flightRecord.getAircraft().getProductProfile().getProduct().getCurrency());
        flightRecordIssued.setPerson(flightRecord.getCrew().stream()
                .filter(member -> member.getCrewMemberRole().equals(CrewMemberRole.PIC))
                .findAny().get().getPerson());
        flightRecordIssued.open();
        //Graba el documento
        documentRepository.saveAndFlush(flightRecordIssued);
        //Contabiliza el documento (crea el asiento)
        accountingEntryService.saveAccountingEntryUsingTemplate(flightRecordIssued);
    }

    public void compensate(ReceiptIssued receipt, FlightRecordIssued flightRecordIssued) {
        this.compensate((Document) receipt, (Document) flightRecordIssued);
    }

    private void compensate(Document parent, Document child) {
        parent.setCompensationDocument(parent);
        parent.setCompensationDate(LocalDate.now());
        parent.close();
        documentRepository.save(parent);
        child.setCompensationDate(LocalDate.now());
        child.setCompensationDocument(parent);
        child.close();
        documentRepository.save(child);
    }

}
