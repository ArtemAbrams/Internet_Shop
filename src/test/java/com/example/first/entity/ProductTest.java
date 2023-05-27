package com.example.first.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class ProductTest {
    @Test
    void testConstructor() {
        Product actualProduct = new Product();
        Country country = new Country();
        actualProduct.setCountry(country);
        actualProduct.setPrice(10.0d);
        ArrayList<Order> productsOrders = new ArrayList<>();
        actualProduct.setProductsOrders(productsOrders);
        actualProduct.setWeight(10.0d);
        assertSame(country, actualProduct.getCountry());
        assertEquals(10.0d, actualProduct.getPrice());
        assertSame(productsOrders, actualProduct.getProductsOrders());
        assertEquals(10.0d, actualProduct.getWeight());
    }

    @Test
    void testConstructor2() {
        Country country = new Country();
        Product actualProduct = new Product("Name", 10.0d, "The characteristics of someone or something", 10.0d, country,
                new ArrayList<>());
        assertSame(country, actualProduct.getCountry());
        assertEquals(10.0d, actualProduct.getWeight());
        assertEquals("Name", actualProduct.getName());
        assertEquals("The characteristics of someone or something", actualProduct.getDescription());
        assertEquals(10.0d, actualProduct.getPrice());
        assertTrue(actualProduct.getProductsOrders().isEmpty());
    }

    @Test
    void testConstructor3() {
        Country country = new Country();
        assertThrows(IllegalArgumentException.class,
                () -> new Product(null, 10.0d, null, 10.0d, country, new ArrayList<>()));

    }

    @Test
    void testConstructor4() {
        Country country = new Country();
        assertThrows(IllegalArgumentException.class,
                () -> new Product("foo", 10.0d, null, 10.0d, country, new ArrayList<>()));

    }
    @Test
    void testGetDescription() {
        assertThrows(IllegalStateException.class, () -> (new Product()).getDescription());
    }

    @Test
    void testGetDescription2() {
        Product product = new Product();
        product.setDescription("foo");
        assertEquals("foo", product.getDescription());
    }

    @Test
    void testGetName() {
        assertThrows(IllegalStateException.class, () -> (new Product()).getName());
    }

    @Test
    void testGetName2() {
        Product product = new Product();
        product.setName("foo");
        assertEquals("foo", product.getName());
    }

    @Test
    void testSetDescription() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        assertEquals("The characteristics of someone or something", product.getDescription());
    }

    @Test
    void testSetDescription2() {
        assertThrows(IllegalArgumentException.class, () -> (new Product()).setDescription(null));
    }
    @Test
    void testSetName() {
        Product product = new Product();
        product.setName("Name");
        assertEquals("Name", product.getName());
    }

    @Test
    void testSetName2() {
        assertThrows(IllegalArgumentException.class, () -> (new Product()).setName(null));
    }
}

