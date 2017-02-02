package com.cua.admin.controllers.rest.core;

import com.cua.admin.model.core.Person;
import com.cua.admin.services.core.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class CoreRestController {

    @Autowired
    private final PersonService personService;

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
    public Person getMember(@PathVariable("id") Integer id) throws Throwable {
        return personService.get(id);
    }

    @RequestMapping(value="/person/", method=RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> create(@RequestBody Person person){
        HttpHeaders headers = new HttpHeaders();
        personService.save(person);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }    

    @RequestMapping(value="/person/{id}", method=RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> update(@RequestBody Person person){
        HttpHeaders headers = new HttpHeaders();
        personService.save(person);
        headers.add("id", person.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }    


}
