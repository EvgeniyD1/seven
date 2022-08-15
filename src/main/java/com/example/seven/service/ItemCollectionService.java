package com.example.seven.service;

import com.example.seven.domain.ItemCollection;
import com.example.seven.repository.ItemCollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemCollectionService {

    private final ItemCollectionRepository collectionRepository;

    public Page<ItemCollection> findAll(Pageable pageable, String username){
        return collectionRepository.findAllByUserUsername(pageable, username);
    }
}
