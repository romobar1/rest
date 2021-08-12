package com.FoodCompanion.REST.exception;

public class RecetaNotFoundException extends RuntimeException{
    public RecetaNotFoundException(String s){
        super(s);
    }
}
