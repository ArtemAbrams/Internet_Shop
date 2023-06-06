package com.example.first.validation;

import com.example.first.validation.filters.PhoneNumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumber.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint {
    String message() default "Phone number is used";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
