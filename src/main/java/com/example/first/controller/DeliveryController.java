package com.example.first.controller;


import com.example.first.exceptions.DeliveryNotFoundException;
import com.example.first.exceptions.TransportNotFoundException;
import com.example.first.entity.Delivery;
import com.example.first.repository.DeliveryRepository;
import com.example.first.repository.TransportRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class DeliveryController
{
    private final DeliveryRepository deliveryRepository;
    private final TransportRepository transportRepository;

    @PostMapping("/CreateDelivery")
    public ResponseEntity<String> SaveDelivery(@RequestParam UUID id, @Valid @RequestBody Delivery newDelivery) {
        var transport = transportRepository.findById(id)
                .orElseThrow(() -> new TransportNotFoundException("Transport with id " + id + " Not Found"));
        newDelivery.setTransport(transport);
        deliveryRepository.saveAndFlush(newDelivery);
        return ResponseEntity.ok("The delivery was created");
    }
    @GetMapping("/addTransportToDelivery/{idTransport}/{idDelivery}")
    public ResponseEntity<String> addTransportToDelivery(@PathVariable UUID idTransport, @PathVariable UUID idDelivery) {
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
    @PutMapping("/updateDelivery/{id}/{transportId}")
    public ResponseEntity<String> updateDelivery(@PathVariable UUID id, @PathVariable UUID transportId, @Valid @RequestBody Delivery delivery) {
        var updateDelivery = deliveryRepository.findById(id)
                .orElseThrow(()-> new DeliveryNotFoundException("Delivery with id " + id + " not Found"));
        var updateTransport = transportRepository.findById(transportId)
                        .orElseThrow(() -> new TransportNotFoundException("Transport with id " + id + " not Found"));
        updateDelivery.setFirstName(delivery.getFirstName());
        updateDelivery.setLastName(delivery.getLastName());
        updateDelivery.setMobilePhone(delivery.getMobilePhone());
        updateDelivery.setTransport(updateTransport);
        deliveryRepository.saveAndFlush(updateDelivery);
        return ResponseEntity.ok("Update was successful");
    }
    @DeleteMapping("/deleteDelivery/{id}")
    public ResponseEntity<HttpStatus> deleteDelivery(@PathVariable UUID id) {
        var delivery = deliveryRepository.findById(id)
                .orElseThrow(()-> new DeliveryNotFoundException("Delivery with id " + id + " not Found"));
        deliveryRepository.delete(delivery);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
