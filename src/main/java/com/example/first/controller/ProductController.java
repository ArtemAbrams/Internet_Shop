package com.example.first.controller;
import com.example.first.DTO.ProductDTO;
import com.example.first.data.ProductData;
import com.example.first.exceptions.CountryNotFoundException;
import com.example.first.exceptions.ProductNotFoundException;
import com.example.first.entity.Product;
import com.example.first.repository.CountryRepository;
import com.example.first.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final CountryRepository countryRepository;
    @PostMapping("/createProduct")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductData productData) {
        if(productData == null)
            return ResponseEntity.badRequest()
                    .body(null);
        var country = countryRepository.findById(UUID.fromString(productData.getCountry()))
                .orElseThrow(() -> new CountryNotFoundException("No this country"));
        Product product = new Product(productData.getName(), productData.getPrice(), productData.getDescription(), productData.getWeight(), country,null);
       productRepository.saveAndFlush(product);
       var productDTO = new ProductDTO(product.getId().toString(), product.getName(), product.getPrice(), product.getDescription(), product.getWeight(), product.getCountry().getName());
       return ResponseEntity.ok(productDTO);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTO>> getAll(){
        var products = productRepository.findAll()
                .stream()
                .map(e -> new ProductDTO(e.getId().toString(), e.getName(), e.getPrice(), e.getDescription(), e.getWeight(), e.getCountry().getName()))
                .toList();
        return ResponseEntity.ok(products);
    }
    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestParam(name = "productId") UUID prId, @Valid @RequestBody ProductData productData) {
        var updateProduct = productRepository.findById(prId)
                .orElseThrow(() -> new ProductNotFoundException("Product with" + prId + "not Found"));
        var country = countryRepository.findById(UUID.fromString(productData.getCountry()))
                .orElseThrow(() -> new CountryNotFoundException("No this country"));
        updateProduct.setCountry(country);
        updateProduct.setName(productData.getName());
        updateProduct.setDescription(productData.getDescription());
        updateProduct.setPrice(productData.getPrice());
        updateProduct.setWeight(productData.getWeight());
        productRepository.save(updateProduct);
        return ResponseEntity.ok("The product was updated");
    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam UUID id) {
        var product = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product with" + id + "not Found"));
        productRepository.delete(product);
        return ResponseEntity.ok("The product was deleted");
    }
}
