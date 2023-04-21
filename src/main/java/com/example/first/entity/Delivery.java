package com.example.first.entity;

import com.example.first.ValidationInterface.PhoneNumberConstraint;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends  AbstractEntity {
    @Length(min = 1, max = 15)
    @NotNull
    @Column(name = "NAME", unique = false)
    private String firstName;

    @Length(min = 1, max = 15)
    @NotNull
    @Column(name = "Surname", unique = false)
    private String LastName;
    @Length(max = 12)
    @NotNull
    @Pattern(regexp = "^380\\d{9}$")
    @Column(name = "Mobile_phone", unique = true)
    @PhoneNumberConstraint
    private String mobilePhone;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "transport_id")
    private Transport transport;

    @OneToMany(mappedBy = "delivery")
    private List<Order> orders = new ArrayList<>();

}