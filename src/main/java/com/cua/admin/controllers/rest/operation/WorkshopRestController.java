package com.cua.admin.controllers.rest.operation;

import com.cua.admin.repositories.it.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/operation")
@RequiredArgsConstructor
public class WorkshopRestController {

    private final UserRepository userService;


}
