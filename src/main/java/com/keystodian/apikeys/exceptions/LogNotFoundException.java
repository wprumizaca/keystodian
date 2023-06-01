package com.keystodian.apikeys.exceptions;

import java.time.LocalDateTime;

public class LogNotFoundException extends RuntimeException{

    public LogNotFoundException(LocalDateTime log_time){
        super("No se puede contrar el LOG con fecha: "+ log_time);
    }
}
