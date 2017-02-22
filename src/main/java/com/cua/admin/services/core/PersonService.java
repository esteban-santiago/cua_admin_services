package com.cua.admin.services.core;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.core.exceptions.MemberNotFoundException;
import com.cua.admin.model.core.exceptions.PersonNotFoundException;
import com.cua.admin.model.core.exceptions.PilotNotFoundException;
import com.cua.admin.repositories.core.PersonRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        () -> new PersonNotFoundException(id));
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

    
    public Page<Person> getAllByPage(Integer number, Integer size) {
        PageRequest request =
            new PageRequest(number - 1, size, Sort.Direction.DESC, "name");
        return personRepository.findAll(request);
    }    

    public Person getMember(Integer id) throws Throwable {
        return this.personRepository.findByIdAndMemberProfileIsNotNull(id).orElseThrow(
        () -> new MemberNotFoundException(id));
    }

    public List<Person> getMembers() {
        return this.personRepository.findByMemberProfileIsNotNull();
    }
    
    public Page<Person> getMembersByPage(Integer number, Integer size) {
        PageRequest request =
            new PageRequest(number - 1, size, Sort.Direction.DESC, "name");
        return personRepository.findByMemberProfileIsNotNull(request);
    }

    
    public Person getPilot(Integer id) throws Throwable {
        return this.personRepository.findByIdAndPilotProfileIsNotNull(id).orElseThrow(
        () -> new PilotNotFoundException(id));
    }
    
    public List<Person> getPilots() {
        return this.personRepository.findByPilotProfileIsNotNull();
    }
    
    public Page<Person> getPilotsByPage(Integer number, Integer size) {
        PageRequest request =
            new PageRequest(number - 1, size, Sort.Direction.DESC, "name");
        return personRepository.findByPilotProfileIsNotNull(request);
    }
}
