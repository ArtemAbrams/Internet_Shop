package com.example.first.exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String message){super(message);}
}
