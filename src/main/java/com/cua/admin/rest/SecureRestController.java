package com.cua.admin.rest;

import com.cua.admin.model.core.Employee;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.User;
import com.cua.admin.model.flight.Aircraft;
import com.cua.admin.repositories.core.EmployeeRepository;
import com.cua.admin.services.AircraftService;
import com.cua.admin.services.MemberService;
import com.cua.admin.services.UserService;
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

    @RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUser(@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/user/lock", method = RequestMethod.POST, headers = "Accept=application/json")
    public void lockUser(@RequestParam(value = "id", required = false, defaultValue = "") Integer id) {
        userService.lock(id);
    }

    @RequestMapping(value = "/user/unlock", method = RequestMethod.POST, headers = "Accept=application/json")
    public void unlockUser(@RequestParam(value = "id", required = false, defaultValue = "") Integer id) {
        userService.unlock(id);
    }
    
    @RequestMapping(value = "/member", method = RequestMethod.GET, headers = "Accept=application/json")
    public Member getMember(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) throws Throwable {
        return memberService.getMember(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
    public Employee getEmployee(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) {
        return employeeRepository.findById(id);
    }

    @RequestMapping(value = "/aircraft", method = RequestMethod.GET, headers = "Accept=application/json")
    public Aircraft getAircraft(@RequestParam(value = "id", required = true) Integer id) {
        return aircraftService.getAircraft(id);
    }
}
