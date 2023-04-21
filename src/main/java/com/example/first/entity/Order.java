package com.example.first.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Delivery delivery;
    @OneToMany(mappedBy = "order")
    private List<Feedback> feedbacks = new ArrayList<>();
    @ManyToMany(mappedBy = "productsOrders")
    private List<Product> products = new ArrayList<>();
}
