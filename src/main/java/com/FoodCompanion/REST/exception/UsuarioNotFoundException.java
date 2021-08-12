package com.FoodCompanion.REST.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String s) {
        super(s);
    }
}
