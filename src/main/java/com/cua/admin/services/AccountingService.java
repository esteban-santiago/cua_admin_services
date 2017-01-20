package com.cua.admin.services;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.model.accounting.documents.Document;
import com.cua.admin.model.accounting.entries.AccountingEntry;
import com.cua.admin.repositories.accounting.AccountRepository;
import com.cua.admin.repositories.accounting.entry.AccountingEntryRepository;
import com.cua.admin.repositories.accounting.documents.AccountingDocumentRepository;
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
    
    
    
    /*
    ** Account Basic Services
    */
    public Account getAccount(Account account) {
        return getAccount(account.getId());
    }

    public Account getAccount(Integer id) {
        return this.accountRepository.findById(id);
    }
    
    public void saveAccount(Account account) {
        this.accountRepository.save(account);
    }
    
    public void saveDocument(Document document) {
        
    }
    
    public void saveAccountingEntry(AccountingEntry entry) {
       this.accountingEntryRepository.saveAndFlush(entry); 
    }

    public List<AccountingEntry> getAll() {
        return this.accountingEntryRepository.findAll();
    }
}
