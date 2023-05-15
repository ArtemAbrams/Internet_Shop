package com.example.first.ValidationInterface.ValidationClass;

import com.example.first.ValidationInterface.PhoneNumberConstraint;
import com.example.first.repository.DeliveryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
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
