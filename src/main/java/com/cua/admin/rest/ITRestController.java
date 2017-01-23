package com.cua.admin.rest;

import com.cua.admin.model.core.User;
import com.cua.admin.repositories.core.UserRepository;
import com.cua.admin.services.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class ITRestController {

    private final UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUser(@RequestParam(value = "id", required = true) Integer id) {
        return userService.get(id);
    }

    @RequestMapping(value="/user", method=RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> save(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        userService.save(user);
        headers.add("id", user.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
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
