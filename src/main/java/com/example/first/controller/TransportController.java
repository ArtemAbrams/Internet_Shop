package com.example.first.controller;

import com.example.first.DTO.TransportDTO;
import com.example.first.entity.Transport;
import com.example.first.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TransportController
{
    private final TransportRepository transportRepository;
    @GetMapping("/AllTransports")
    public List<TransportDTO> GetUsers()
    {
        return transportRepository.findAll()
                .stream()
                .map(TransportDTO::new)
                .toList();
    }
}
