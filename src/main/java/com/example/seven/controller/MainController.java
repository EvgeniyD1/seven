package com.example.seven.controller;

import com.example.seven.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @GetMapping
    public String main(@AuthenticationPrincipal User user, Model model){
        if (user!=null){
            model.addAttribute("profile", user.getUsername());
            model.addAttribute("access", user.getRole());
        }
        return "index";
    }
}
