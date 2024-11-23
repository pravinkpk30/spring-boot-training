package com.dc.pharmacy.controller;

import com.dc.pharmacy.dto.UserInfo;
import com.dc.pharmacy.exception.ResourceNotFoundException;

import com.dc.pharmacy.service.IUserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/****
 *
 * @RestController: Indicates that this class is a REST controller, meaning that Spring Boot will handle requests to the /api/users endpoint,
 * and each method will return a JSON response by default.
 *
 * @RequestMapping("/api/users"): Sets a base URL path for all endpoints within this controller.
 *
 * @GetMapping, @PostMapping, @PutMapping, @DeleteMapping: Shorthand annotations that specify the HTTP method for each endpoint.
 * They make the controller more readable and RESTful.
 *
 *
 */
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {
    private List<UserInfo> users = new ArrayList<>();

    @GetMapping
    public List<UserInfo> getAllUsers() {
        return users;
    }

    @Autowired
    private IUserService userService;

    @PostMapping
    public UserInfo createUser(@Valid @RequestBody UserInfo user) {
        users.add(user);
        return user;
    }

    @PostMapping
    @RequestMapping("/adduser")
    public void addUser(@Valid @RequestBody UserInfo user) {
        userService.addUser(user);
    }

    @RequestMapping("/finduser/{email}")
    public UserInfo findUser(@RequestParam("email") String email) {
        return userService.findUser(email);
    }

    @PreAuthorize("#id == authentication.name")
    @GetMapping("/{id}")
    @ResponseBody
    public UserInfo getUserById(@PathVariable Long id) {
        // return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        return userService.findUserById(id);
    }

    @GetMapping("/{id}/{name}")
    @ResponseBody
    public UserInfo getUserByIdAndName(@PathVariable Long id, @PathVariable("name") String username) {
        return users.stream().filter(user -> user.getId().equals(id) && user.getName().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UserInfo updateUser(@PathVariable Long id, @RequestBody UserInfo user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return user;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @GetMapping("/greet")
    public String greetUser(@RequestParam(defaultValue = "Guest") String name, @RequestParam String ssn, @RequestParam(value = "id", required= true) int userId) {
        return "Hello " + name + ", your ssn is " + ssn + " - user id is - " + userId ;
    }
}
