package com.cua.admin.services.finance;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import com.cua.admin.model.operation.flight.FlightRecord;
import com.cua.admin.repositories.accounting.entry.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.entry.TemplateEntryRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cua.admin.repositories.finance.documents.DocumentRepository;

/**
 *
 * @author esantiago
 */
@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {
    @Autowired
    private final AccountingEntryRepository accountingEntryRepository; 
    
    @Autowired
    private final DocumentRepository<Document> documentRepository;
    
    @Autowired
    private final TemplateEntryRepository templateEntryRepository;
    
    //Documentos Financieros
    public void save(Document document) {
        document.close();
        this.documentRepository.saveAndFlush(document);
    }
    
    public void saveDocument(FlightRecord flightRecord) {
        FlightRecordIssued flightRecordIssued = new FlightRecordIssued();
        flightRecordIssued.setReferencedDocumentId(flightRecord.getId());
        flightRecordIssued.setAmount(flightRecord.getAmountOfHours() * flightRecord.getAircraft().getProductProfile().getProduct().getPrice());
        flightRecordIssued.setCurrency(flightRecord.getAircraft().getProductProfile().getProduct().getCurrency());
        flightRecordIssued.setPerson(flightRecord.getCrew().stream()
                .filter(member -> member.getCrewMemberRole().equals(CrewMemberRole.PIC))
                .findAny().get().getPerson());
        flightRecordIssued.close();
        documentRepository.saveAndFlush(flightRecordIssued);
    }
    
    //Asientos contables
    public void saveAccountingEntryUsingTemplate(Document document){
        TemplateEntry template = templateEntryRepository.findByDocumentType(document.getDocumentType());
        this.accountingEntryRepository.save(template.getAccountingEntry(document));
    }
    
    public void compensate(ReceiptIssued receipt, FlightRecordIssued flightRecord) {
        this.compensate((Document) receipt, (Document) receipt);
    }
    
    private void compensate(Document parent, Document child) {
        
    }
    
    public void delete(Document document) {
        this.documentRepository.delete(document.getId());
    }
    
}
