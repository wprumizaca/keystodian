package com.keystodian.apikeys.exceptions;

public class IdUserNotFoundException extends RuntimeException{

    public IdUserNotFoundException(Long id){
        super("No se puede contrar el usuario con ID: "+ id);
    }
}
