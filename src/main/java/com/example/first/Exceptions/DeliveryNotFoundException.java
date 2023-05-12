package com.example.first.Exceptions;

public class DeliveryNotFoundException extends RuntimeException
{
    public  DeliveryNotFoundException(String message) {
        super(message);
    }
}
