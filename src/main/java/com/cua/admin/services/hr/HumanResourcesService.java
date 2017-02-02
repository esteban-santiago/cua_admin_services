package com.cua.admin.services.hr;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.hr.exceptions.EmployeeNotFoundException;
import com.cua.admin.repositories.core.PersonRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HumanResourcesService {
    
    @Autowired
    private PersonRepository personRepository;
    
    
    public Person getEmployee(Integer id) throws Throwable {
        return this.personRepository.findByIdAndEmployeeProfileIsNotNull(id).orElseThrow(
        () -> new EmployeeNotFoundException(id));
    }
    
    public List getEmployees() {
        return this.personRepository.findByEmployeeProfileIsNotNull();
    }

} 
