package com.example.first.entity;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transports")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transport extends AbstractEntity
{
    @Length(min = 1, max = 15)
    @NotNull
    @Column(name = "Transport_NAME", unique = true)
    private String name;

    @OneToMany(mappedBy = "transport", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Delivery> deliveries = new ArrayList<>();
}
