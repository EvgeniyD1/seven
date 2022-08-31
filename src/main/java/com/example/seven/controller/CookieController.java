package com.example.seven.controller;

import com.example.seven.request.CookieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CookieController {

    @GetMapping("/readCookie")
    public String readCookie(@CookieValue(value = "theme", defaultValue = "dark") String theme) {
        return theme;
    }

    @PostMapping(value = "/setCookie")
    public ResponseEntity<?> setCookie(HttpServletResponse response, @RequestBody CookieRequest request) {
        Cookie cookie = new Cookie("theme", request.getTheme());
        response.addCookie(cookie);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
