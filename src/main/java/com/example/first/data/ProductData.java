package com.example.first.data;

import com.example.first.ValidationInterface.NameConstraint;
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
public class ProductData implements Serializable{
    @NotNull
    @NameConstraint
    private String name;

    @DecimalMin(value = "0.01", inclusive = true)
    @DecimalMax(value = "100000", inclusive = true)
    private double price;
    @NotNull
    @Size(min = 12, max = 800)
    private String description;

    @DecimalMin(value = "0.01", inclusive = true)
    @DecimalMax(value = "1000", inclusive = true)
    private double weight;

    @NotNull
    private String country;
}
