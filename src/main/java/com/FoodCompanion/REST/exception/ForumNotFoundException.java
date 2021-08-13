package com.FoodCompanion.REST.exception;

public class ForumNotFoundException extends RuntimeException{
    public ForumNotFoundException(String s){
        super(s);
    }
}
