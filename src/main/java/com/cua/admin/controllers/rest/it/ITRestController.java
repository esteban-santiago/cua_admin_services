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
@RequestMapping("/sapi/it")
public class ITRestController {

    @Autowired
    private final UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> get(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @RequestMapping(value = "/user", 
            params = { "page", "size" }, 
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<User>> getAllPaginated(
            @RequestParam(value = "page") Integer page, 
            @RequestParam(value = "size") Integer size) {
        return ResponseEntity.ok(userService.getAllByPage(page, size));
    }   
    
    @RequestMapping(value="/user", method=RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }    
    
    @RequestMapping(value="/user/{id}", method=RequestMethod.PUT, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<User> update(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }    

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return ResponseEntity.ok(id);
    }


    @RequestMapping(value = "/user/lock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Integer> lockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.lock(id);
        return ResponseEntity.ok(id);    
    }

    @RequestMapping(value = "/user/unlock", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Integer> unlockUser(@RequestParam(value = "id", required = true) Integer id) {
        userService.unlock(id);
        return ResponseEntity.ok(id);    
    }
}
