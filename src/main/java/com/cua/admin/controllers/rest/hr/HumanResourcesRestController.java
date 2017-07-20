package com.cua.admin.controllers.rest.hr;

import com.cua.admin.model.core.Person;
import com.cua.admin.services.hr.HumanResourcesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/hr")
@RequiredArgsConstructor
public class HumanResourcesRestController {
    @Autowired
    private final HumanResourcesService humanResourcesService;
  
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Person> get(@RequestParam(value = "id", required = true) Integer id) throws Throwable {
        return ResponseEntity.ok(this.humanResourcesService.getEmployee(id));
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(humanResourcesService.getEmployees());
    }
}
