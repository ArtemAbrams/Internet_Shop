package com.example.first.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @PrePersist
    public void toCreate() {
        setCreateDate(LocalDateTime.now());
    }

    @PreUpdate
    public void toUpdate() {
        setUpdateDate(LocalDateTime.now());
    }

}
