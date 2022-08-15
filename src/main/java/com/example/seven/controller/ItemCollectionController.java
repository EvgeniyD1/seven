package com.example.seven.controller;

import com.example.seven.domain.ItemCollection;
import com.example.seven.service.ItemCollectionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
@AllArgsConstructor
public class ItemCollectionController {

    private final ItemCollectionService collectionService;

    @GetMapping("/{username}")
    public Page<ItemCollection> findAll(
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable,
            @PathVariable String username){
        return collectionService.findAll(pageable, username);
    }
}
