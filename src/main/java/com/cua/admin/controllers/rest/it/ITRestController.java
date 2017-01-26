package com.cua.admin.controllers.rest.it;

import com.cua.admin.model.it.User;
import com.cua.admin.services.it.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sapi")
@RequiredArgsConstructor
public class ITRestController {

    private final UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        System.out.println(id);
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    
    @RequestMapping(value="/user/", method=RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> create(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        userService.save(user);
        headers.add("id", user.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }    
    
    @RequestMapping(value="/user/{id}", method=RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> update(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        System.out.println("PUT---");
        userService.save(user);
        headers.add("id", user.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }    

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("id", id.toString());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/user/lock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void lockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.lock(id);
    }

    @RequestMapping(value = "/user/unlock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void unlockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.unlock(id);
    }


}
