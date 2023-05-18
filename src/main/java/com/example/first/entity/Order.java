package com.example.first.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbstractEntity {

    @Column(name = "date_of_create_order")
    private LocalDateTime localDateTime;
    @Column(name = "Address")
    @NotNull
    @Size(min = 5, max = 100)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Delivery delivery;
    @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE})
    private List<Feedback> feedbacks = new ArrayList<>();
    @ManyToMany(mappedBy = "productsOrders", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Product> products = new ArrayList<>();
    @Override
    public void toCreate() {setCreateDate(LocalDateTime.now());
        setLocalDateTime(LocalDateTime.now());}
}
