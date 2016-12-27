package com.cua.admin.rest;

import com.cua.admin.model.core.Employee;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.User;
import com.cua.admin.repositories.EmployeeRepository;
import com.cua.admin.repositories.MemberRepository;
import com.cua.admin.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class SecureRestService {

    private final UserRepository userService;
    private final MemberRepository memberRepository;
    private final EmployeeRepository employeeRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUser(@RequestParam(value = "id", required = false, defaultValue = "100") Integer id) {
        System.out.println("Llamado el metodo!!:  " + id);
        User pp = userService.findByName("Esteban").get(0);
        User p = new User("esteban", "santiago");
        p.setId(id);
        System.out.println("Usuario: " + p.toString());
        return pp;
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET, headers = "Accept=application/json")
    public Member getPerson(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) {
        return memberRepository.findById(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
    public Employee getEmployee(@RequestParam(value = "id", required = true, defaultValue = "1") Integer id) {
        return employeeRepository.findById(id);
    }

}
