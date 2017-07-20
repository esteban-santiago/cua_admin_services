package com.cua.admin.services.administration;

import com.cua.admin.model.administration.contract.Contract;
import com.cua.admin.model.core.Person;
import com.cua.admin.repositories.administration.ContractRepository;
import java.time.LocalDate;
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
    ** Contract Basic Services
    */
    public Contract get(Long id) {
        return this.contractRepository.findById(id).get();
    }
    
    public Contract save(Contract contract) {
        return this.contractRepository.save(contract);
    }
    
    public List<Contract> getAll() {
        return this.contractRepository.findAll();
    }
    
    public List<Contract> getByPerson(Person person) {
        return this.contractRepository.findByPersonId(person);
    }
    
    public List<Contract> getByValidity(LocalDate now) {
        return this.contractRepository.findByValidityFromAfterAndValidityToBefore(now, now);
    }
    
}
