package com.example.first.Exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){super(message);}
}
