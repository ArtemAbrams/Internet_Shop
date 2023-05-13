package com.example.first.controller;

import com.example.first.DTO.TransportDTO;
import com.example.first.Exceptions.TransportNotFoundException;
import com.example.first.entity.Transport;
import com.example.first.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthPopupMenuUI;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TransportController
{
    private final TransportRepository transportRepository;
    @PostMapping("/createTransport")
    public ResponseEntity<String> createTransport(@Valid @RequestBody Transport transport){
        transportRepository.saveAndFlush(transport);
        return ResponseEntity.ok("The new transport was created");
    }
    @PutMapping("/updateTransport")
    public ResponseEntity<String> updateTransport(@RequestParam UUID id, @Valid @RequestBody Transport transport)
    {
        var updateTransport = transportRepository.findById(id)
                .orElseThrow(() -> new TransportNotFoundException("Transport wis id" + id + "was not Found"));
        updateTransport.setName(transport.getName());
        transportRepository.saveAndFlush(updateTransport);
        return ResponseEntity.ok("You update the transport");
    }
    @DeleteMapping("/deleteTransport")
    public ResponseEntity<String> deleteTransport(@RequestParam UUID id)
    {
        var deleteTransport = transportRepository.findById(id)
                .orElseThrow(()-> new TransportNotFoundException("Transport wis id" + id + "was not Found"));
        transportRepository.delete(deleteTransport);
        return ResponseEntity.ok("You delete the transport");
    }
}
