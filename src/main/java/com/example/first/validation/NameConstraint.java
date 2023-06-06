package com.example.first.validation;

import com.example.first.validation.filters.NameProduct;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = NameProduct.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NameConstraint {
    String message() default "Name is used";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
