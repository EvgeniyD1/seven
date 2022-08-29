package com.example.seven.controller;

import com.example.seven.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/{name}")
    public Set<String> findFirst5ByNameStartsWith(@PathVariable String name) {
        return tagService.findFirst5ByNameStartsWith(name);
    }
}
