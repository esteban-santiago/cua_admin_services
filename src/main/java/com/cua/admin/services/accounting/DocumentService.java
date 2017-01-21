package com.cua.admin.services.accounting;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.documents.Document;
import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.entry.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.documents.AccountingDocumentRepository;
import com.cua.admin.repositories.accounting.entry.TemplateEntryRepository;
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
public class DocumentService {
    @Autowired
    private final AccountingEntryRepository accountingEntryRepository; 
    
    @Autowired
    private final AccountingDocumentRepository<Document> accountingDocumentRepository;
    
    @Autowired
    private final TemplateEntryRepository templateEntryRepository;
        
    //Documentos Contables
    public void saveDocument(Document document) {
        document.close();
        this.accountingDocumentRepository.saveAndFlush(document);
    }
    
    //Asientos contables
    public void saveAccountingEntryUsingTemplate(Document document){
        TemplateEntry template = templateEntryRepository.findByDocumentType(document.getDocumentType());
        this.accountingEntryRepository.save(template.getAccountingEntry(document));
    }
    
}
