package com.cua.admin.services.accounting;

import com.cua.admin.model.accounting.Account;
import com.cua.admin.repositories.accounting.AccountRepository;
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
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    /*
    ** Account Basic Services
    */
    public Account get(Integer id) {
        return this.accountRepository.findById(id);
    }

    public Account get(Account account) {
        return this.get(account.getId());
    }
    
    public void save(Account account) {
        this.accountRepository.save(account);
    }
    
    public List<Account> getAll() {
        return this.accountRepository.findAll();
    }
    
    
}
