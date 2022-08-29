package com.example.seven.service;

import com.example.seven.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Set<String> findFirst5ByNameStartsWith(String name) {
        Set<String> tagNames = new HashSet<>();
        tagRepository.findFirst5ByNameStartsWith(name).forEach(tag -> tagNames.add(tag.getName()));
        return tagNames;
    }
}
