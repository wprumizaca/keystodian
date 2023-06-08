package com.keystodian.apikeys.expose.dto.dtoLogin;


import lombok.Getter;
import lombok.Setter;



/**
 * Los campos nulos no se van a devolver.
 * Cuando generamos token desde refresh token no le pasamos todos los parametros.
 */


@Getter @Setter
public class LoginResponse {

    private Long id;
    private String username;


}
