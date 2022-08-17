package com.example.seven.repository;

import com.example.seven.domain.Cluster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClusterRepository extends JpaRepository<Cluster, Long> {

    Page<Cluster> findAllByUserUsername(Pageable pageable, String username);
}