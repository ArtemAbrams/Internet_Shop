package com.example.first.controller;

import com.example.first.Exceptions.CountryNotFoundException;
import com.example.first.Exceptions.ProductNotFoundException;
import com.example.first.Exceptions.TransportNotFoundException;
import com.example.first.entity.Order;
import com.example.first.entity.Product;
import com.example.first.repository.CountryRepository;
import com.example.first.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final CountryRepository countryRepository;
    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(@RequestParam UUID id, @Valid @RequestBody Product product)
    {
       var country = countryRepository.findById(id)
               .orElseThrow(()-> new TransportNotFoundException("Transport with" + id + "not Found"));
       product.setCountry(country);
       productRepository.saveAndFlush(product);
       return ResponseEntity.ok("The product was created");
    }
    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestParam(name = "productId") UUID prId, @RequestParam(name = "countryId") UUID id, @Valid @RequestBody Product product)
    {
        var updateProduct = productRepository.findById(prId)
                .orElseThrow(() -> new ProductNotFoundException("Product with" + prId + "not Found"));
        var country = countryRepository.findById(id)
                .orElseThrow(()-> new CountryNotFoundException("Country with" + id + "not Found"));
        updateProduct.setCountry(country);
        updateProduct.setName(product.getName());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setWeight(product.getWeight());
        return ResponseEntity.ok("The product was updated");
    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam UUID id)
    {
        var product = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product with" + id + "not Found"));
        productRepository.delete(product);
        return ResponseEntity.ok("The product was deleted");
    }
}
