package com.cua.admin.repositories.administration;

import com.cua.admin.model.administration.contract.Contract;
import com.cua.admin.model.core.Person;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    //Contract findById(Integer id);
    List<Contract> findByPersonId(Person person);
    List<Contract> findByValidityFromAfterAndValidityToBefore(LocalDate from, LocalDate to);
    
}
