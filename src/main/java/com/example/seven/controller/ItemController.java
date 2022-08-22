package com.example.seven.controller;

import com.example.seven.domain.item.Item;
import com.example.seven.request.ItemRequest;
import com.example.seven.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id,
                       @RequestBody ItemRequest request){
        return itemService.updateItem(id, request);
    }

    @PutMapping("/uploadImg/{id}")
    public String uploadImg(@PathVariable Long id,
                            @RequestParam(value = "file") MultipartFile file){
        return itemService.uploadImg(id, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        itemService.delete(id);
    }
}
