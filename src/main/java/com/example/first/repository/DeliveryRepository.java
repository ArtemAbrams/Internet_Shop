package com.example.first.repository;


import com.example.first.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID>
{
    public boolean existsDeliveryByMobilePhone(String mobilePhone);
}
