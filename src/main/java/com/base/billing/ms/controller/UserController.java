package com.base.billing.ms.controller;

import com.base.billing.ms.model.dao.User;
import com.base.billing.ms.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}/authenticate")
    public User getUserByUsernameAndPassword(@PathVariable String username, @RequestParam String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{username}/role-status")
    public void updateUserStatusAndRole(@PathVariable String username, @RequestParam String status, @RequestParam String role) {
        userService.updateUserStatusAndRole(username, status, role);
    }

    @PutMapping("/{username}/password")
    public void updateUserPassword(@PathVariable String username, @RequestParam String password) {
        userService.updateUserPassword(username, password);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
