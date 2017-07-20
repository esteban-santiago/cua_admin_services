package com.cua.admin.controllers.rest.core;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.core.exceptions.MemberNotFoundException;
import com.cua.admin.model.core.exceptions.PersonNotFoundException;
import com.cua.admin.model.core.exceptions.PilotNotFoundException;
import com.cua.admin.model.core.profiles.PilotRating;
import com.cua.admin.services.core.PersonService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sapi/core")
public class CoreRestController {

    @Autowired
    private final PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Person>> get() {
        return ResponseEntity.ok(personService.getAll());
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> get(@PathVariable("id") Integer id) throws Throwable {
        try {
            return ResponseEntity.ok(personService.get(id));
        } catch (PersonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        personService.save(person);
        return ResponseEntity.ok(person);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        personService.save(person);
        return ResponseEntity.ok(person);
    }

    @RequestMapping(value = "/sapi/person",
            params = {"page", "size"},
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<Person>> getAllPaginated(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {
        return ResponseEntity.ok(personService.getAllByPage(page, size));
    }

    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> getMember(@PathVariable("id") Integer id) throws Throwable {
        try {
            return ResponseEntity.ok(personService.getMember(id));
        } catch (MemberNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Person>> getMembers() {
        return ResponseEntity.ok(personService.getMembers());
    }

    @RequestMapping(value = "/pilot/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> getPilot(@PathVariable("id") Integer id) throws Throwable {
        try {
            return ResponseEntity.ok(personService.getPilot(id));
        } catch(PilotNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/pilot", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Person>> getPilots() {
        return ResponseEntity.ok(personService.getPilots());
    }

    @RequestMapping(value = "/pilot/rating", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getPilotRatings() {
        Map ratings = new HashMap<>();
        String values[];
        for (int i = 0; i < PilotRating.values().length; i = i + 1) {
            values = new String[2];
            values[0] = PilotRating.values()[i].name();
            values[1] = PilotRating.values()[i].getDescription();
            ratings.put(i, values);
        }
        return ResponseEntity.ok(ratings);
    }
}
