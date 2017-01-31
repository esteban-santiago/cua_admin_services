package com.cua.admin.controllers.rest.it;

import com.cua.admin.model.it.User;
import com.cua.admin.services.it.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ITRestController {

    @Autowired
    private final UserService userService;

    @RequestMapping(value = "/sapi/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/sapi/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sapi/user", 
            params = { "page", "size" }, 
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<User>> getAllPaginated(
            @RequestParam(value = "page") Integer page, 
            @RequestParam(value = "size") Integer size) {
        return new ResponseEntity<>(userService.getAllByPage(page, size), HttpStatus.OK);
    }   
    
    @RequestMapping(value="/sapi/user", method=RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> create(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        userService.save(user);
        headers.add("id", user.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }    
    
    @RequestMapping(value="/sapi/user/{id}", method=RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Void> update(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        userService.save(user);
        headers.add("id", user.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }    

    @RequestMapping(value = "/sapi/user/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("id", id.toString());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/sapi/user/lock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Void> lockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.lock(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("id", id.toString());
        return new ResponseEntity<>(headers, HttpStatus.OK);    
    }

    @RequestMapping(value = "/sapi/user/unlock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Void> unlockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.unlock(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("id", id.toString());
        return new ResponseEntity<>(headers, HttpStatus.OK);    
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Boolean> login(@RequestParam(value = "user", required = true) User user) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
