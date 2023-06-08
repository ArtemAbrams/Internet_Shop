package com.example.first.controller;


import com.example.first.exceptions.DeliveryNotFoundException;
import com.example.first.exceptions.OrderNotFoundException;
import com.example.first.exceptions.ProductNotFoundException;
import com.example.first.repository.DeliveryRepository;
import com.example.first.repository.OrderRepository;
import com.example.first.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.first.entity.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestParam UUID id, @Valid @RequestBody Order order){
        var delivery = deliveryRepository.findById(id)
                        .orElseThrow(() -> new DeliveryNotFoundException("Delivery with" + id + "Not Found"));
        order.setDelivery(delivery);
        orderRepository.saveAndFlush(order);
        return ResponseEntity.ok("You create the new order");
    }
    @PutMapping("/updateOrder")
    public ResponseEntity<String> updateOrder(@RequestParam(name = "deliveryId") UUID id, @RequestParam UUID orderId, @Valid @RequestBody Order order) {
        var updateOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    return new OrderNotFoundException("Order with id" + orderId + "Not Found");
                });
        var delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new  DeliveryNotFoundException("Delivery with" + id + "Not Found"));
        updateOrder.setDelivery(delivery);
        updateOrder.setAddress(order.getAddress());
        orderRepository.saveAndFlush(updateOrder);
        return ResponseEntity.ok("The order was updated");
    }
    @DeleteMapping("/deleteOrder")
    public ResponseEntity<String> deleteOrder(@RequestParam UUID id)
    {
        var order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order with id" + id + "Not Found"));
        orderRepository.delete(order);
        return ResponseEntity.ok("The order was delete");
    }
    @PostMapping("/addProductToOrder")
    public ResponseEntity<String> addProduct(@RequestParam UUID productId, @RequestParam UUID orderId)
    {
        var product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product with" + productId + "Not Found"));
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with" + orderId + "Not Found"));
        var orderList = product.getProductsOrders();
        orderList.add(order);
        product.setProductsOrders(orderList);
        productRepository.saveAndFlush(product);
        return ResponseEntity.ok("The product was added to order");
    }
  @PutMapping ("/deleteProductInOrder")
    public ResponseEntity<String> deleteProduct(@RequestParam UUID orderId, @RequestParam UUID productId, @RequestParam int number) {
      var product = productRepository.findById(productId)
              .orElseThrow(() -> new ProductNotFoundException("Product with" + productId + "Not Found"));
      var order = orderRepository.findById(orderId)
              .orElseThrow(() -> new OrderNotFoundException("Order with" + orderId + "Not Found"));
      var listOrder = product.getProductsOrders()
              .stream()
              .filter(elem -> elem.getId() == orderId)
              .collect(Collectors.toList());
      if(listOrder.size()<number)
          return ResponseEntity.badRequest()
                  .body("You cannot delete" + number + "products");
      var deleteList = listOrder.stream()
              .skip(number)
              .collect(Collectors.toList());
      var newListOrder = product.getProductsOrders()
              .stream()
              .filter(elem -> elem.getId() != orderId)
              .collect(Collectors.toCollection(ArrayList::new));
      newListOrder.addAll(deleteList);
      product.setProductsOrders(newListOrder);
      productRepository.saveAndFlush(product);
      return ResponseEntity.ok("You delete " + number + " products");
  }
}
