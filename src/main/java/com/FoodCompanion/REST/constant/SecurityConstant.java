package com.FoodCompanion.REST.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 43_000_000; //millisec
    public static final String TOKEN_HEADER = "Bearer "; //Token owner
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_VERIFIED = "Token to be verified";
    public static final String TOKEN_CAN_NOT_BE_VERIFIED = "Token can not be to be verified";
    public static final String GET_FOODCOMPANION_LLC = "FoodCompanion, LLC";
    public static final String GET_FOODCOMPANION_ADMINISTRATION = "Recetas Managment Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to be log in to access this page";
    public static final String ACCES_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/add", "/user/resetpassword/**"};
    }
