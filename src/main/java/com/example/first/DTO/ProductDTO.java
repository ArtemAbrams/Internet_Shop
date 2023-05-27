package com.example.first.DTO;

import com.example.first.ValidationInterface.NameConstraint;
import com.example.first.entity.Country;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
