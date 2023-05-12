package com.example.first.controller;

import com.example.first.Exceptions.FeedbackNotFoundException;
import com.example.first.Exceptions.OrderNotFoundException;
import com.example.first.entity.Feedback;
import com.example.first.repository.FeedbackRepository;
import com.example.first.repository.OrderRepository;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.spi.RegisterableService;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class FeedbackController {
    private final FeedbackRepository feedbackRepository;
    private final OrderRepository orderRepository;
    @PostMapping("/createFeedback")
    public ResponseEntity<String> CreateFeedback(@RequestParam() UUID id, @Valid @RequestBody Feedback feedback) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not Found"));
        feedback.setOrder(order);
        feedbackRepository.saveAndFlush(feedback);
        return ResponseEntity.ok("You add feedback");
    }
    @DeleteMapping("deleteFeedback/{id}")
    public ResponseEntity<String> DeleteFeedback(@PathVariable UUID id) {
        var feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback with id " + id + " not Found"));
        feedbackRepository.delete(feedback);
        return ResponseEntity.ok("You delete this feedback");
    }
}
