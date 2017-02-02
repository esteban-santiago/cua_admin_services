package com.cua.admin.controllers.rest.hr;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.hr.profiles.Employee;
import com.cua.admin.model.core.profiles.Member;
import com.cua.admin.model.it.User;
import com.cua.admin.model.operation.flight.Aircraft;
import com.cua.admin.services.operation.flight.AircraftService;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.hr.HumanResourcesService;
import com.cua.admin.services.it.UserService;
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
    private final HumanResourcesService humanResourcesService;
  
    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
    public Person getEmployee(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) throws Throwable {
        return this.humanResourcesService.getEmployee(id);
    }
}
