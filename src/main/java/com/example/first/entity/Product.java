package com.example.first.entity;

import com.example.first.ValidationInterface.NameConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
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
  @NotNull
  @NameConstraint
  private String name;
  @Column(name = "price")
  @NotNull
  @DecimalMin(value = "0.01", inclusive = true)
  @DecimalMax(value = "100000", inclusive = true)
  private double price;
  @Column(name = "description")
  @NotNull
  @Size(min = 12, max = 800)
  private String description;
  @Column(name = "weight_in_kg")
  @NotNull
  @DecimalMin(value = "0.01", inclusive = true)
  @DecimalMax(value = "1000", inclusive = true)
  private double weight;
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Country country;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "products_orders", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "Id"),
          inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "Id"))
  private List<Order> productsOrders = new ArrayList<>();
}
