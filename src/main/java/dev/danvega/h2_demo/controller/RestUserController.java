package dev.danvega.h2_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.service.UserService;

import java.util.List;

@RestController
public class RestUserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> users() {
        List<User> users = userService.getAllUserApi();
        return users;
    }

    @PostMapping("/api/addUsers")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
