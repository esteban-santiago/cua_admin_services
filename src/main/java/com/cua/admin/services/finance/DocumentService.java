package com.cua.admin.services.finance;

import com.cua.admin.model.finance.Document;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import com.cua.admin.repositories.accounting.entry.AccountingEntryRepository;
import com.cua.admin.repositories.finance.documents.AccountingDocumentRepository;
import com.cua.admin.repositories.accounting.entry.TemplateEntryRepository;
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
public class DocumentService {
    @Autowired
    private final AccountingEntryRepository accountingEntryRepository; 
    
    @Autowired
    private final AccountingDocumentRepository<Document> accountingDocumentRepository;
    
    @Autowired
    private final TemplateEntryRepository templateEntryRepository;
        
    //Documentos Contables
    public void save(Document document) {
        document.close();
        this.accountingDocumentRepository.saveAndFlush(document);
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
        this.accountingDocumentRepository.delete(document.getId());
    }
    
}
