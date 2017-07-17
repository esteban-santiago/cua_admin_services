package com.cua.admin.repositories.administration;

import com.cua.admin.model.administration.Contract;
import com.cua.admin.model.core.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    //Contract findById(Integer id);
}
