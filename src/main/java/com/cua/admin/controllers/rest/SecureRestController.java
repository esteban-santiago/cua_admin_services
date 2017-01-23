package com.cua.admin.controllers.rest;

import com.cua.admin.model.hr.Employee;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.it.User;
import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.repositories.hr.EmployeeRepository;
import com.cua.admin.services.core.AircraftService;
import com.cua.admin.services.core.MemberService;
import com.cua.admin.services.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class SecureRestController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final MemberService memberService;
    @Autowired
    private final AircraftService aircraftService;
    @Autowired
    private final EmployeeRepository employeeRepository;

    
    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = "application/json")
    public Member getMember(@PathVariable("id") Integer id) throws Throwable {
        return memberService.get(id);
    }

}
