package com.cua.admin.rest;

import com.cua.admin.model.core.User;
import com.cua.admin.repositories.core.UserRepository;
import com.cua.admin.services.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class AdministrationRestController {

    private final UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUser(@RequestParam(value = "id", required = true) Integer id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/user/lock", method = RequestMethod.POST, headers = "Accept=application/json")
    public void lockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.lock(id);
    }

    @RequestMapping(value = "/user/unlock", method = RequestMethod.POST, headers = "Accept=application/json")
    public void unlockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.unlock(id);
    }


}
