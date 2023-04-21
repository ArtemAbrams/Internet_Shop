package com.example.first.repository;

import com.example.first.entity.Transport;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransportRepository extends JpaRepository<Transport, UUID> {
  @EntityGraph(value = "post-entity-graph")
  List<Transport> findByName(String name);
}