package com.cua.admin.controllers.rest.operation;

import com.cua.admin.repositories.it.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class InventoryRestController {

    private final UserRepository userService;


}
