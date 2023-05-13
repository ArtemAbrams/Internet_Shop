package com.example.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends AbstractEntity{
    @Column(name = "name_country")
    private String name;
    @OneToMany(mappedBy = "country", cascade = {CascadeType.REMOVE})
    private List<Product> products = new ArrayList<>();

}
