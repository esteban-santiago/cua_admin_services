package com.cua.admin.services.accounting;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.entry.AccountingEntryRepository;
import com.cua.admin.repositories.finance.documents.AccountingDocumentRepository;
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
