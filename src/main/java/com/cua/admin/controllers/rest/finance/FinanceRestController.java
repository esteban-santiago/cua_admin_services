package com.cua.admin.controllers.rest.finance;

import com.cua.admin.model.it.User;
import com.cua.admin.repositories.it.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class FinanceRestController {
    @Autowired
    private final UserRepository userService;


}
