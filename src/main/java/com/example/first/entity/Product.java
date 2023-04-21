package com.example.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {
  @Column(name = "name_product")
  private String name;
  @Column(name = "price")
  private double price;
  @Column(name = "description")
  private String description;
  @Column(name = "weight_in_kg")
  private double weight;
  @ManyToOne(fetch = FetchType.LAZY)
  private Country country;

  @ManyToMany
  @JoinTable(name = "products_orders", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "Id"),
          inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "Id"))
  private List<Order> productsOrders = new ArrayList<>();
}
