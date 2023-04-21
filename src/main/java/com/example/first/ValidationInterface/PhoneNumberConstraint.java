package com.example.first.ValidationInterface;

import com.example.first.ValidationClass.PhoneNumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumber.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint
{
    String message() default "Phone number is used";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}