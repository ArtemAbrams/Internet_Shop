package com.example.first.validation.filters;

import com.example.first.validation.PhoneNumberConstraint;
import com.example.first.repository.DeliveryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PhoneNumber implements ConstraintValidator<PhoneNumberConstraint, String> {
    private final DeliveryRepository deliveryRepository;
    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext)
    {
          return !deliveryRepository.existsDeliveryByMobilePhone(phoneNumber);
    }
}
