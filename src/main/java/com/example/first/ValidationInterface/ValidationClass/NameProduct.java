package com.example.first.ValidationInterface.ValidationClass;
import com.example.first.ValidationInterface.NameConstraint;
import com.example.first.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//@RequiredArgsConstructor
@RequiredArgsConstructor
public class NameProduct implements ConstraintValidator<NameConstraint, String> {
    private final ProductRepository productRepository;
    /*public NameProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
        System.out.println("param-constr");
    }*/

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !productRepository.existsProductByName(name);
    }
}
