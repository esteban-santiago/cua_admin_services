package com.cua.admin.rest;

import com.cua.admin.model.core.User;
import com.cua.admin.repositories.core.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class WorkshopRestController {

    private final UserRepository userService;


}
