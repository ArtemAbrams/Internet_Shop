package com.example.first.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private String id;

    private String name;

    private double price;

    private String description;

    private double weight;

    private String countryName;
}
