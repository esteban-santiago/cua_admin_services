package com.cua.admin.services.sales;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.sales.exceptions.CustomerNotFoundException;
import com.cua.admin.repositories.core.PersonRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesService {
    
    @Autowired
    private PersonRepository personRepository;
    
    
    public Person getCustomer(Integer id) throws Throwable {
        return this.personRepository.findByIdAndCustomerProfileIsNotNull(id).orElseThrow(
        () -> new CustomerNotFoundException(id));
    }
    
    public List getCustomers() {
        return this.personRepository.findByCustomerProfileIsNotNull();
    }

} 
