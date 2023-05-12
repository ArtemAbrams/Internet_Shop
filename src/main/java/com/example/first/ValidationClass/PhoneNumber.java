package com.example.first.ValidationClass;

import com.example.first.ValidationInterface.PhoneNumberConstraint;
import com.example.first.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
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
          return deliveryRepository.existsDeliveryByMobilePhone(phoneNumber);
    }
}
