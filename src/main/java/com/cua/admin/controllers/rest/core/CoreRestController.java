package com.cua.admin.controllers.rest.core;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.core.profiles.PilotRating;
import com.cua.admin.services.core.PersonService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class CoreRestController {

    @Autowired
    private final PersonService personService;
    
    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Person>> get() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> get(@PathVariable("id") Integer id) throws Throwable {
        return new ResponseEntity<>(personService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value="/person", method=RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Person> create(@RequestBody Person person){
        //HttpHeaders headers = new HttpHeaders();
        personService.save(person);
        //headers.add("id", person.getId().toString());
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }    

    @RequestMapping(value="/person/{id}", method=RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Person> update(@RequestBody Person person){
        //HttpHeaders headers = new HttpHeaders();
        personService.save(person);
        //headers.add("id", person.getId().toString());
        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/sapi/person", 
            params = { "page", "size" }, 
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<Person>> getAllPaginated(
            @RequestParam(value = "page") Integer page, 
            @RequestParam(value = "size") Integer size) {
        return new ResponseEntity<>(personService.getAllByPage(page, size), HttpStatus.OK);
    }   

    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> getMember(@PathVariable("id") Integer id) throws Throwable {
        return new ResponseEntity<>(personService.getMember(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Person>> getMembers() {
        return new ResponseEntity<>(personService.getMembers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pilot/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> getPilot(@PathVariable("id") Integer id) throws Throwable {
                return new ResponseEntity<>(personService.getPilot(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/pilot", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Person>> getPilots() {
        return new ResponseEntity<>(personService.getPilots(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pilot/rating", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getPilotRatings() {
        Map ratings = new HashMap<>();
        String values[];
        for(int i = 0 ; i < PilotRating.values().length; i = i + 1) {
            values = new String[2];
            values[0] = PilotRating.values()[i].name();
            values[1] = PilotRating.values()[i].getDescription();
            ratings.put(i, values);
        }
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}

