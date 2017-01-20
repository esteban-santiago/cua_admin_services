package com.cua.admin.services;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.documents.Document;
import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.entry.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.documents.AccountingDocumentRepository;
import com.cua.admin.repositories.accounting.entry.TemplateEntryRepository;
import java.util.List;
import java.util.Set;
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
public class AccountingService {
    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final AccountingEntryRepository accountingEntryRepository; 
    
    @Autowired
    private final AccountingDocumentRepository accountingDocumentRepository;
    
    @Autowired
    private final TemplateEntryRepository templateEntryRepository;
    
    
    /*
    ** Account Basic Services
    */
    public Account getAccount(Integer id) {
        return this.accountRepository.findById(id);
    }
    
    public void saveAccount(Account account) {
        this.accountRepository.save(account);
    }
    
    //Documentos Contables
    public void saveDocument(Document document) {
        this.accountingDocumentRepository.saveAndFlush(document);
    }
    
    //Asientos contables
    public void saveAccountingEntryUsingTemplate(Document document){
        TemplateEntry entry = templateEntryRepository.findByDocumentType(document.getDocumentType());
        AccountingEntry accountingEntry = entry.getAccountingEntry(document); 
        this.saveAccountingEntry(accountingEntry);
        System.out.println("Guardando: " + entry.getAccountingEntry(document) );
    }
    
    public void saveAccountingEntry(AccountingEntry entry) {
       this.accountingEntryRepository.save(entry); 
    }

    public List<AccountingEntry> getAllAccountingEntries() {
        return this.accountingEntryRepository.findAll();
    }
}
