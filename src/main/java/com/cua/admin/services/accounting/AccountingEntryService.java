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
public class AccountingEntryService {
    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final AccountingEntryRepository accountingEntryRepository; 
    
    @Autowired
    private final AccountingDocumentRepository<Document> accountingDocumentRepository;
    
    
    public void saveDocument(Document document) {
        document.close();
        this.accountingDocumentRepository.saveAndFlush(document);
    }
        
    public void save(AccountingEntry entry) {
       this.accountingEntryRepository.save(entry);
    }

    public List<AccountingEntry> getAll() {
        return this.accountingEntryRepository.findAll();
    }
}
