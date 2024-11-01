package com.dc.pharmacy.controller;

import com.dc.pharmacy.dto.User;
import com.dc.pharmacy.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

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
@RequestMapping("/api/users")
@Validated
public class UserController {
    private List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        users.add(user);
        return user;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/{id}/{name}")
    @ResponseBody
    public User getUserByIdAndName(@PathVariable Long id, @PathVariable("name") String username) {
        return users.stream().filter(user -> user.getId().equals(id) && user.getName().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
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
