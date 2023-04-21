package com.example.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback extends AbstractEntity{
    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
