package com.cua.admin.controllers.operation.rest;

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
public class InventoryRestController {

    private final UserRepository userService;


}
