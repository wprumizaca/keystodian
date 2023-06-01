package com.keystodian.apikeys.expose.dto.dtoApp;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CreateAppRequest {

    @NonNull
    private Long id;

    @NonNull
    private String app;

    @NotBlank
    private String password;

}
