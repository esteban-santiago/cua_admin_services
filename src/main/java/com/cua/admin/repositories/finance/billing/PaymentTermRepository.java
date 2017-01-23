package com.cua.admin.repositories.finance.billing;

import com.cua.admin.model.finance.billing.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Integer> {

    PaymentTerm findById(Integer id);
    
}
