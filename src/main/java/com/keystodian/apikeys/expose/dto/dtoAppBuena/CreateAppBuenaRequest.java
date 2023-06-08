package com.keystodian.apikeys.expose.dto.dtoAppBuena;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CreateAppBuenaRequest {

    @NonNull
    private Long  id;

    @NonNull
    private String plataforma;

    @NonNull
    private String usuario;

    @NotBlank
    private String password;

}
