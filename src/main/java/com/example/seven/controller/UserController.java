package com.example.seven.controller;

import com.example.seven.domain.User;
import com.example.seven.request.UserUpdateRequest;
import com.example.seven.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PermitAll
    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PutMapping("/{username}/updateProfile")
    public void updateProfile(@PathVariable String username,
                              @RequestBody UserUpdateRequest request) {
        userService.updateProfile(username, request);
    }

    @PutMapping("/{username}/updateAbout")
    public void updateAbout(@PathVariable String username,
                            @RequestBody UserUpdateRequest request) {
        userService.updateAbout(username, request);
    }

    @PutMapping("/{username}/updateAdminProp")
    public void updateAdminProp(@PathVariable String username,
                                @RequestBody UserUpdateRequest request) {
        userService.updateAdminProp(username, request);
    }
}
