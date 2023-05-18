package com.example.first.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.first.Exceptions.CountryNotFoundException;
import com.example.first.Exceptions.ProductNotFoundException;
import com.example.first.Exceptions.TransportNotFoundException;
import com.example.first.entity.Country;
import com.example.first.entity.Product;
import com.example.first.repository.CountryRepository;
import com.example.first.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

class ProductControllerTest {
    @Test
    void testCreateProduct() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.saveAndFlush(Mockito.<Product>any())).thenReturn(new Product());
        CountryRepository countryRepository = mock(CountryRepository.class);
        Country country = new Country();
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(country));
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID id = UUID.randomUUID();
        Product product = new Product();
        ResponseEntity<String> actualCreateProductResult = productController.createProduct(id, product);
        assertEquals("The product was created", actualCreateProductResult.getBody());
        assertEquals(200, actualCreateProductResult.getStatusCodeValue());
        assertTrue(actualCreateProductResult.getHeaders().isEmpty());
        verify(productRepository).saveAndFlush(Mockito.<Product>any());
        verify(countryRepository).findById(Mockito.<UUID>any());
        assertSame(country, product.getCountry());
    }

    @Test
    void testCreateProduct2() {

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.saveAndFlush(Mockito.<Product>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Country()));
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID id = UUID.randomUUID();
        assertThrows(ProductNotFoundException.class, () -> productController.createProduct(id, new Product()));
        verify(productRepository).saveAndFlush(Mockito.<Product>any());
        verify(countryRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testCreateProduct3() {

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.saveAndFlush(Mockito.<Product>any())).thenReturn(new Product());
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.empty());
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID id = UUID.randomUUID();
        assertThrows(TransportNotFoundException.class, () -> productController.createProduct(id, new Product()));
        verify(countryRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testCreateProduct4() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.saveAndFlush(Mockito.<Product>any())).thenReturn(new Product());
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Country()));
        ProductController productController = new ProductController(productRepository, countryRepository);
        productController.createProduct(UUID.randomUUID(), null);
    }

    @Test
    void testUpdateProduct2() {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Product()));
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID prId = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        assertThrows(ProductNotFoundException.class, () -> productController.updateProduct(prId, id, new Product()));
        verify(productRepository).findById(Mockito.<UUID>any());
        verify(countryRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testUpdateProduct3() {
        Product product = mock(Product.class);
        doThrow(new ProductNotFoundException("An error occurred")).when(product).setCountry(Mockito.<Country>any());
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(product));
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Country()));
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID prId = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        assertThrows(ProductNotFoundException.class, () -> productController.updateProduct(prId, id, new Product()));
        verify(productRepository).findById(Mockito.<UUID>any());
        verify(product).setCountry(Mockito.<Country>any());
        verify(countryRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testUpdateProduct4() {

        new ProductNotFoundException("An error occurred");
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.empty());
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Country()));
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID prId = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        assertThrows(ProductNotFoundException.class, () -> productController.updateProduct(prId, id, new Product()));
        verify(productRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testUpdateProduct5() {
        Product product = mock(Product.class);
        doThrow(new ProductNotFoundException("An error occurred")).when(product).setCountry(Mockito.<Country>any());
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(product));
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.empty());
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID prId = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        assertThrows(CountryNotFoundException.class, () -> productController.updateProduct(prId, id, new Product()));
        verify(productRepository).findById(Mockito.<UUID>any());
        verify(countryRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testUpdateProduct6() {


        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(new Product());
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Product()));
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Country()));
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID prId = UUID.randomUUID();
        UUID id = UUID.randomUUID();

        Product product = new Product();
        product.setName("foo");
        product.setDescription("foo");
        ResponseEntity<String> actualUpdateProductResult = productController.updateProduct(prId, id, product);
        assertEquals("The product was updated", actualUpdateProductResult.getBody());
        assertEquals(200, actualUpdateProductResult.getStatusCodeValue());
        assertTrue(actualUpdateProductResult.getHeaders().isEmpty());
        verify(productRepository).save(Mockito.<Product>any());
        verify(productRepository).findById(Mockito.<UUID>any());
        verify(countryRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testUpdateProduct7() {

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.save(Mockito.<Product>any())).thenThrow(new ProductNotFoundException("An error occurred"));
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Product()));
        CountryRepository countryRepository = mock(CountryRepository.class);
        when(countryRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Country()));
        ProductController productController = new ProductController(productRepository, countryRepository);
        UUID prId = UUID.randomUUID();
        UUID id = UUID.randomUUID();

        Product product = new Product();
        product.setName("foo");
        product.setDescription("foo");
        assertThrows(ProductNotFoundException.class, () -> productController.updateProduct(prId, id, product));
        verify(productRepository).save(Mockito.<Product>any());
        verify(productRepository).findById(Mockito.<UUID>any());
        verify(countryRepository).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link ProductController#updateProduct(UUID, UUID, Product)}
     */
    @Test
    void testUpdateProduct8() {

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<UUID>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));
        ProductController productController = new ProductController(productRepository, mock(CountryRepository.class));
        UUID prId = UUID.randomUUID();
        UUID id = UUID.randomUUID();

        Product product = new Product();
        product.setName("Name");
        product.setDescription("foo");
        assertThrows(ProductNotFoundException.class, () -> productController.updateProduct(prId, id, product));
        verify(productRepository).findById(Mockito.<UUID>any());
    }

    @Test
    void testDeleteProduct() {

        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).delete(Mockito.<Product>any());
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Product()));
        ProductController productController = new ProductController(productRepository, mock(CountryRepository.class));
        ResponseEntity<String> actualDeleteProductResult = productController.deleteProduct(UUID.randomUUID());
        assertEquals("The product was deleted", actualDeleteProductResult.getBody());
        assertEquals(200, actualDeleteProductResult.getStatusCodeValue());
        assertTrue(actualDeleteProductResult.getHeaders().isEmpty());
        verify(productRepository).findById(Mockito.<UUID>any());
        verify(productRepository).delete(Mockito.<Product>any());
    }

    @Test
    void testDeleteProduct2() {

        ProductRepository productRepository = mock(ProductRepository.class);
        doThrow(new ProductNotFoundException("An error occurred")).when(productRepository).delete(Mockito.<Product>any());
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.of(new Product()));
        ProductController productController = new ProductController(productRepository, mock(CountryRepository.class));
        assertThrows(ProductNotFoundException.class, () -> productController.deleteProduct(UUID.randomUUID()));
        verify(productRepository).findById(Mockito.<UUID>any());
        verify(productRepository).delete(Mockito.<Product>any());
    }

    @Test
    void testDeleteProduct3() {

        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).delete(Mockito.<Product>any());
        when(productRepository.findById(Mockito.<UUID>any())).thenReturn(Optional.empty());
        ProductController productController = new ProductController(productRepository, mock(CountryRepository.class));
        assertThrows(ProductNotFoundException.class, () -> productController.deleteProduct(UUID.randomUUID()));
        verify(productRepository).findById(Mockito.<UUID>any());
    }
}

