package com.keystodian.apikeys.exceptions;

public class NombreExistenteException extends RuntimeException{

    public NombreExistenteException(){
        super("El usuario introducido ya existe");
    }
}
