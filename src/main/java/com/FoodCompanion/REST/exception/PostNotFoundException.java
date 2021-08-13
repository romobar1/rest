package com.FoodCompanion.REST.exception;


public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String s){
        super(s);
    }
}
