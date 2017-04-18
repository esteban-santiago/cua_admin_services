package com.cua.admin.controllers.rest.it;

import com.cua.admin.model.it.User;
import com.cua.admin.services.it.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/it")
public class ITRestUnsecuredController {

    @Autowired
    private final UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Boolean> login(@RequestParam(value = "user", required = true) User user) {
        return ResponseEntity.ok(true);
    }
}
