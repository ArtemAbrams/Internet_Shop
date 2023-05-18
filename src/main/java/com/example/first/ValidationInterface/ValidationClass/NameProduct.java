package com.example.first.ValidationInterface.ValidationClass;


import com.example.first.ValidationInterface.NameConstraint;
import com.example.first.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@RequiredArgsConstructor
public class NameProduct implements ConstraintValidator<NameConstraint, String> {
    private final ProductRepository productRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !productRepository.existsProductByName(name);
    }
}
