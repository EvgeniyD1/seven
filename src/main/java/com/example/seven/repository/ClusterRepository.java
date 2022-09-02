package com.example.seven.repository;

import com.example.seven.domain.Cluster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClusterRepository extends JpaRepository<Cluster, Long> {

    Page<Cluster> findAllByUserUsername(Pageable pageable, String username);

    @EntityGraph(attributePaths = {"user"})
    @Query("select clucter from Cluster clucter order by clucter.items.size desc")
    Page<Cluster> findAllOrderByItemsSize(Pageable pageable);

    @EntityGraph(value = "cluster")
    @Query("select cluster from Cluster cluster where cluster.id = ?1")
    Optional<Cluster> findOneForDelete(Long id);
}
