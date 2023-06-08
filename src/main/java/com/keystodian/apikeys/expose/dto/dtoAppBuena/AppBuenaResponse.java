package com.keystodian.apikeys.expose.dto.dtoAppBuena;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AppBuenaResponse {

    private String plataforma;
    private String usuario;
    private String password;


//    private LocalDateTime creation_date;

    public AppBuenaResponse(String plataforma, String usuario, String password) {
        this.plataforma = plataforma;
        this.usuario = usuario;
        this.password = password;
    }

}