package com.example.seven.repository;

import com.example.seven.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @EntityGraph(value = "item")
    Page<Item> findAllByClusterId(Pageable pageable, Long id);

    @EntityGraph(value = "item")
    Page<Item> findAllByIdIsNotNull(Pageable pageable);

    @Query(value =
            "select id from (select items.id as id from clusters join items on clusters.id = items.cluster_id " +
                    "where to_tsvector(clusters.name) || to_tsvector(items.name) @@ to_tsquery(:text)" +
                    "union" +
                    " select items.id as id from items join comments on items.id = comments.item_id" +
                    " where to_tsvector(comments.text) || to_tsvector(items.name) @@ to_tsquery(:text)) results",
            nativeQuery = true)
    List<Long> getIds(@Param("text") String text);

    @EntityGraph(value = "item")
    @Query("select item from Item item where item.id in :ids")
    Page<Item> fulTextSearch(Pageable pageable, @Param("ids") List<Long> ids);


    @EntityGraph(attributePaths = {"tags", "typeOne", "typeTwo", "typeThree", "typeFour", "typeFive"})
    @Query("select item from Item item where item.id = ?1")
    Optional<Item> findOneForDelete(Long id);
}
