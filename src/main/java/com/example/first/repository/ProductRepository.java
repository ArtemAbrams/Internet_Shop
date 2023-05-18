package com.example.first.repository;


import com.example.first.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  boolean existsProductByName(String name);

}
