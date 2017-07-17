package com.cua.admin.controllers.rest.operation.workshop;

import com.cua.admin.repositories.it.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi/operation/workshop")
@RequiredArgsConstructor
public class WorkshopRestController {

    private final UserRepository userService;


}
