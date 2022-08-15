package com.example.seven.repository;

import com.example.seven.domain.ItemCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCollectionRepository extends JpaRepository<ItemCollection, Long> {

    Page<ItemCollection> findAllByUserUsername(Pageable pageable, String username);
}
