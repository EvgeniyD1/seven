package com.example.seven.controller;

import com.example.seven.domain.item.Item;
import com.example.seven.request.ItemRequest;
import com.example.seven.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/collection/{id}")
    public Page<Item> findAll(
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long id) {
        return itemService.findAll(pageable, id);
    }

    @GetMapping("{id}")
    public Item findOne(@PathVariable Long id) {
        return itemService.findOne(id);
    }

    @PostMapping("/{clusterId}/new")
    public Item create(@PathVariable Long clusterId,
                       @RequestBody ItemRequest request) {
        return itemService.create(clusterId, request);
    }
}
