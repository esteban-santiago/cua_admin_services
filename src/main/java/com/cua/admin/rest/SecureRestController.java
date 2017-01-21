package com.cua.admin.rest;

import com.cua.admin.model.core.Employee;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.User;
import com.cua.admin.model.flight.Aircraft;
import com.cua.admin.repositories.core.EmployeeRepository;
import com.cua.admin.services.core.AircraftService;
import com.cua.admin.services.core.MemberService;
import com.cua.admin.services.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    
    @RequestMapping(value = "/member", method = RequestMethod.GET, headers = "Accept=application/json")
    public Member getMember(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) throws Throwable {
        return memberService.get(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
    public Employee getEmployee(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) {
        return employeeRepository.findById(id);
    }

    @RequestMapping(value = "/aircraft", method = RequestMethod.GET, headers = "Accept=application/json")
    public Aircraft getAircraft(@RequestParam(value = "id", required = true) Integer id) {
        return aircraftService.get(id);
    }
}
