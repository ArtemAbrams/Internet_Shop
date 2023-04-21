package com.example.first.DTO;

import com.example.first.entity.Transport;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransportDTO {
    private String name;
    private UUID id;

    public  TransportDTO(Transport transport) {
        this.name = transport.getName();
        this.id = transport.getId();
    }

}
