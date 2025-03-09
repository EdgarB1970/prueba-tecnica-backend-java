package com.banco_microservice_cliente.microservice_cliente.exception;

public class CustomException extends  RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
