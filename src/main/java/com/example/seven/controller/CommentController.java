package com.example.seven.controller;

import com.example.seven.domain.Comment;
import com.example.seven.domain.User;
import com.example.seven.dto.CommentDto;
import com.example.seven.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /*+*/
    @GetMapping("/item/{id}")
    public Page<CommentDto> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                 @PathVariable Long id){
        return commentService.findAll(pageable, id);
    }

    /*+*/
    @PostMapping("/{itemId}/new")
    public CommentDto save(@PathVariable("itemId") Long id,
                        @AuthenticationPrincipal User user,
                        @RequestBody CommentDto commentDto){
        return commentService.save(id, user, commentDto);
    }

    @PutMapping("/{id}")
    public CommentDto update(@PathVariable Long id,
                          @RequestBody CommentDto commentDto){
        return commentService.update(id, commentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        commentService.delete(id);
    }
}
