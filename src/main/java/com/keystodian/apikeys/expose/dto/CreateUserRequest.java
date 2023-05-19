package com.keystodian.apikeys.expose.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * DTO con la información necesaria para registrar un nuevo usuario en base de datos
 {
 "username": "user1"
 "email": "user1@jwt.io"
 "password": "admin"

 }
 */

@Getter @Setter
public class CreateUserRequest {

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NotBlank
    private String password;

}
