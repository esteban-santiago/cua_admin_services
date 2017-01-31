package com.cua.admin.controllers.rest.core;

import com.cua.admin.model.hr.Employee;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.it.User;
import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.repositories.hr.EmployeeRepository;
import com.cua.admin.services.operation.flight.AircraftService;
import com.cua.admin.services.core.MemberService;
import com.cua.admin.services.it.UserService;
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
    private final MemberService memberService;

    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = "application/json")
    public Member getMember(@PathVariable("id") Integer id) throws Throwable {
        return memberService.get(id);
    }

    @RequestMapping(value="/member/", method=RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> create(@RequestBody Member member){
        HttpHeaders headers = new HttpHeaders();
        memberService.save(member);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }    

    @RequestMapping(value="/member/{id}", method=RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> update(@RequestBody Member member){
        HttpHeaders headers = new HttpHeaders();
        memberService.save(member);
        headers.add("id", member.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }    


}
