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
public class HumanResourcesRestController {

    @Autowired
    private final EmployeeRepository employeeRepository;
  
    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
    public Employee getEmployee(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) {
        return employeeRepository.findById(id);
    }
}
