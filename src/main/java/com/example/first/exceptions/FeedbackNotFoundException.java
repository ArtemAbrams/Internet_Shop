package com.example.first.exceptions;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException(String message){super(message);}
}
