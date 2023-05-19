package com.keystodian.apikeys.exceptions;




public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username){
        super("No se puede contrar el usuario con NOMBRE: "+ username);
    }
}
