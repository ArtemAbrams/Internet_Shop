package com.example.first.exceptions;

public class TransportNotFoundException extends RuntimeException
{
    public  TransportNotFoundException(String message) {
        super(message);
    }
}
