package com.cua.admin.controllers.rest.hr;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.it.User;
import com.cua.admin.services.hr.HumanResourcesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Person> getEmployee(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) throws Throwable {
        return new ResponseEntity<>(this.humanResourcesService.getEmployee(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Person>> getEmployees() {
        return new ResponseEntity<>(humanResourcesService.getEmployees(), HttpStatus.OK);
    }
}
