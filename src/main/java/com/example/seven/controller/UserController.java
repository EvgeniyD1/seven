package com.example.seven.controller;

import com.example.seven.dto.UserDto;
import com.example.seven.request.UserRequest;
import com.example.seven.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public UserDto returnUserDto(@PathVariable String username) {
        return userService.returnUserDto(username);
    }

    //    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('USER')")
    @PermitAll
    @PutMapping("/{username}/updateProfile")
    public void updateProfile(@PathVariable String username,
                              @RequestBody UserRequest request) {
        userService.updateProfile(username, request);
    }

    //    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('USER')")
    @PermitAll
    @PutMapping("/{username}/updateAbout")
    public void updateAbout(@PathVariable String username,
                            @RequestBody UserRequest request) {
        userService.updateAbout(username, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{username}/updateAdminProp")
    public void updateAdminProp(@PathVariable String username,
                                @RequestBody UserRequest request) {
        userService.updateAdminProp(username, request);
    }
}
