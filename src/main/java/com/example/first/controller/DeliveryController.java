package com.example.first.controller;

import ch.qos.logback.core.model.Model;
import com.example.first.Exceptions.DeliveryNotFoundException;
import com.example.first.Exceptions.TransportNotFoundException;
import com.example.first.ValidationClass.PhoneNumber;
import com.example.first.entity.Delivery;
import com.example.first.entity.Transport;
import com.example.first.repository.DeliveryRepository;
import com.example.first.repository.TransportRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class DeliveryController
{
    private final DeliveryRepository deliveryRepository;
    private final TransportRepository transportRepository;
    @PostMapping("/CreateDelivery")
    public Delivery SaveDelivery(@Valid @RequestBody Delivery newDelivery)
    {
        return deliveryRepository.saveAndFlush(newDelivery);
    }
    @GetMapping("/addTransportToDelivery/{idTransport}/{idDelivery}")
    public ResponseEntity<String> addTransportToDelivery(@PathVariable UUID idTransport, @PathVariable UUID idDelivery)
    {
        try {
            var delivery = deliveryRepository.findById(idDelivery)
                    .orElseThrow(() -> new DeliveryNotFoundException("Delivery with id " + idDelivery + " not Found"));
            var transport = transportRepository.findById(idTransport)
                    .orElseThrow(()-> new TransportNotFoundException("Transport with id " + idTransport + " Not Found"));
            delivery.setTransport(transport);
            deliveryRepository.saveAndFlush(delivery);
        }
        catch (Exception ex)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
        return ResponseEntity
                .ok("All Good");
    }
}
