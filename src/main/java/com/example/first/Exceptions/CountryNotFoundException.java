package com.example.first.Exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String message){super(message);}
}
