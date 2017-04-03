package com.cua.admin.repositories.finance.billing;

import com.cua.admin.model.finance.billing.PaymentMethod;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    Optional<PaymentMethod> findById(Integer id);
    
    
}
