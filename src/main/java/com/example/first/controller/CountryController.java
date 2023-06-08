package com.example.first.controller;


import com.example.first.exceptions.TransportNotFoundException;
import com.example.first.entity.Country;
import com.example.first.repository.CountryRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class CountryController {
    private final CountryRepository countryRepository;
    @PostMapping("/createCountry")

    public ResponseEntity<String> createCountry(@Valid @RequestBody Country country){
        countryRepository.saveAndFlush(country);
        return ResponseEntity.ok("The new country was created");
    }
    @PutMapping("/updateCountry")
    public ResponseEntity<String> updateCountry(@RequestParam UUID id, @Valid @RequestBody Country country)
    {
        var updateCountry = countryRepository.findById(id)
                .orElseThrow(() -> new TransportNotFoundException("Transport wis id" + id + "was not Found"));
        updateCountry.setName(country.getName());
        countryRepository.saveAndFlush(updateCountry);
        return ResponseEntity.ok("You update the transport");
    }
    @DeleteMapping("/deleteCountry")
    public ResponseEntity<String> deleteCountry(@RequestParam UUID id)
    {
        var deleteCountry = countryRepository.findById(id)
                .orElseThrow(()-> new TransportNotFoundException("Transport wis id" + id + "was not Found"));
        countryRepository.delete(deleteCountry);
        return ResponseEntity.ok("You delete the transport");
    }
}
