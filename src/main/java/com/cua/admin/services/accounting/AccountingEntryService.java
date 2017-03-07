package com.cua.admin.services.accounting;

import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import com.cua.admin.model.accounting.entries.exceptions.TemplateEntryNotFoundException;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.entry.AccountingEntryRepository;
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
    private final TemplateEntryRepository templateEntryRepository;
    
    @Autowired
    private final AccountingEntryRepository accountingEntryRepository; 
    
    //Asientos contables
    public void saveAccountingEntryUsingTemplate(Document document){
        TemplateEntry template = templateEntryRepository
                .findByDocumentType(document.getDocumentType()).get();
        this.accountingEntryRepository.save(template.getAccountingEntry(document));
    }
    
    public void save(AccountingEntry entry) {
       this.accountingEntryRepository.save(entry);
    }

    public List<AccountingEntry> getAll() {
        return this.accountingEntryRepository.findAll();
    }
}
