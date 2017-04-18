package com.cua.admin.services.finance.billing;

import com.cua.admin.model.finance.billing.exceptions.PaymentMethodNotFoundException;
import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.billing.PaymentMethod;
import com.cua.admin.model.hr.exceptions.EmployeeNotFoundException;
import com.cua.admin.repositories.core.PersonRepository;
import com.cua.admin.repositories.finance.billing.PaymentMethodRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentMethodService {
    
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    
    
    public PaymentMethod get(Integer id) throws Throwable {
        return this.paymentMethodRepository.findById(id).orElseThrow(
        () -> new PaymentMethodNotFoundException(id));
    }
    
    public List getAll() {
        return this.paymentMethodRepository.findAll();
    }

} 
