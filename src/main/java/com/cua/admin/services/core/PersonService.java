package com.cua.admin.services.core;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.core.exceptions.MemberNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cua.admin.repositories.core.PersonRepository;

/**
 *
 * @author esantiago
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;
    
    public Person get(Integer id) throws Throwable {
        return personRepository.findById(id).orElseThrow(
        () -> new MemberNotFoundException(id));
    }
    
    public Person get(Person person) throws Throwable {
        return this.get(person.getId());
    }
    
    public void save(Person person) {
        this.personRepository.save(person);
    }

    public void delete(Integer id) {
        this.personRepository.delete(id);
    }

    public void delete(Person person) {
        this.personRepository.delete(person.getId());
    }

    public List<Person> getAll() {
        return this.personRepository.findAll();
    }
}
