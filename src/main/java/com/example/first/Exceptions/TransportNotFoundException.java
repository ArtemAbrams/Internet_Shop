package com.example.first.Exceptions;

public class TransportNotFoundException extends RuntimeException
{
    public  TransportNotFoundException(String message)
    {
        super(message);
    }
}
