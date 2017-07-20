package com.cua.admin.controllers.rest.administration;

import com.cua.admin.model.administration.MembershipFee;
import com.cua.admin.model.core.Person;
import com.cua.admin.services.administration.MembershipFeeService;
import com.cua.admin.services.core.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/administration")
@RequiredArgsConstructor
public class MembershipFeeRestController {

    @Autowired
    private final MembershipFeeService membershipFeeService;
    
    @Autowired
    private final PersonService personService;

    @RequestMapping(value = "/membership_fee", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<MembershipFee>> get() {
        return ResponseEntity.ok(membershipFeeService.getAll());
    }

    @RequestMapping(value = "/membership_fee/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<MembershipFee> getById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(membershipFeeService.get(id));
    }

    @RequestMapping(value = "/membership_fee/person/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<MembershipFee>> getByPersonId(@PathVariable("id") Person person) throws Throwable {
        return ResponseEntity.ok(membershipFeeService.getByPerson(personService.get(person)));
    }

}
