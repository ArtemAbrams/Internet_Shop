package com.example.first.repository;

import com.example.first.entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  boolean existsProductByName(String name);
  @Query(value = "SELECT COUNT(*) FROM products_orders op WHERE op.order_id=:Id", nativeQuery = true)
  Long countProductsByOrderId(String Id);
  /*@Query(value = "INSERT INTO products_orders(product_id, order_id) VALUES (:productId, :orderId)" ,nativeQuery = true)
  void saveInProductsOrders(String productId, String orderId);*/

}
