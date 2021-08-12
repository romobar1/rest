package com.FoodCompanion.REST.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String s){
        super(s);
    }
}
