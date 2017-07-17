package com.cua.admin.services.administration;

import com.cua.admin.model.administration.Contract;
import com.cua.admin.repositories.administration.ContractRepository;
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
public class ContractService {
    @Autowired
    private final ContractRepository contractRepository;

    /*
    ** Account Basic Services
    */
    public Contract get(Integer id) {
        return this.contractRepository.findById(id).get();
    }
    
    public void save(Contract contract) {
        this.contractRepository.save(contract);
    }
    
    public List<Contract> getAll() {
        return this.contractRepository.findAll();
    }
    
    
}
