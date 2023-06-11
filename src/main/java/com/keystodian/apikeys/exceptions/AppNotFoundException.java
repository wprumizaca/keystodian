package com.keystodian.apikeys.exceptions;

public class AppNotFoundException extends RuntimeException{

    public AppNotFoundException(String app){
        super("No se puede encontrar la plataforma con nombre: "+ app);
    }
}
