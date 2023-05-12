package com.example.first.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){super(message);}
}
