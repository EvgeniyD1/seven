package com.example.seven.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @GetMapping
    public String main(@AuthenticationPrincipal String username, Model model){
        //todo
        if (!username.isBlank()){
            model.addAttribute("profile", username);
        }
        return "index";
    }
}
