package com.example.seven.controller;

import com.example.seven.domain.User;
import com.example.seven.dto.ClusterDto;
import com.example.seven.service.ClusterService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/collections")
@AllArgsConstructor
public class ClusterController {

    private final ClusterService clusterService;

    @GetMapping("/{username}")
    public Page<ClusterDto> findAll(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable String username) {
        return clusterService.findAllByUserUsername(pageable, username);
    }

    @GetMapping()
    public Page<ClusterDto> findAllOrderByItemsSize(
            @PageableDefault(size = 5) Pageable pageable) {
        return clusterService.findAllOrderByItemsSize(pageable);
    }

    @GetMapping("/collection/{id}")
    public ClusterDto findOne(@PathVariable Long id) {
        return clusterService.findOne(id);
    }

    @PostMapping
    public ClusterDto create(@AuthenticationPrincipal User user,
                             @RequestBody ClusterDto clusterDto) {
        return clusterService.create(user, clusterDto);
    }

    @PutMapping("/collection/uploadImg/{id}")
    public String uploadImg(@PathVariable Long id,
                            @RequestParam(value = "file") MultipartFile file) {
        return clusterService.uploadImg(id, file);
    }

    @PutMapping("/collection/{id}")
    public ClusterDto update(@PathVariable Long id,
                          @RequestBody ClusterDto clusterDto) {
        return clusterService.update(id, clusterDto);
    }

    @DeleteMapping("/collection/{id}")
    public void delete(@PathVariable Long id) {
        clusterService.delete(id);
    }
}
