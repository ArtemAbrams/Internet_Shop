package com.example.first.ValidationClass;

import com.example.first.ValidationInterface.NameConstraint;
import com.example.first.ValidationInterface.PhoneNumberConstraint;
import com.example.first.repository.ProductRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class NameProduct implements ConstraintValidator<NameConstraint, String> {
    private final ProductRepository productRepository;
    @Override
    public void initialize(NameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsProductByName(name);
    }
}
