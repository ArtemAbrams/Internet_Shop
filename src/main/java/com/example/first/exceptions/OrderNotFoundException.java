package com.example.first.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){super(message);}
}
