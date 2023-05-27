package com.example.first.entity;


import com.example.first.ValidationInterface.NameConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Country country;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinTable(name = "products_orders", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "Id"),
          inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "Id"))
  private List<Order> productsOrders = new ArrayList<>();
}
