package com.cua.admin.repositories.billing;

import com.cua.admin.model.billing.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    PaymentMethod findById(Integer id);
}
