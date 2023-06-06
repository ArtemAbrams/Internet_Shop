package com.example.first.exceptions;

public class DeliveryNotFoundException extends RuntimeException
{
    public  DeliveryNotFoundException(String message) {
        super(message);
    }
}
