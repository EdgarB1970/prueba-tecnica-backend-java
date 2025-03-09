package com.banco_microservice_cuenta.microservice_cuenta.exception;

public class CustomException extends  RuntimeException{
    public CustomException(String message){
        super(message);
    }
}