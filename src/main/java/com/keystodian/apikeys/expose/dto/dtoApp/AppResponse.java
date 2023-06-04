package com.keystodian.apikeys.expose.dto.dtoApp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter

public class AppResponse {

    private String app;
    private String password;
    private LocalDateTime creation_date;

    public AppResponse(String app, String password, LocalDateTime creation_date) {
        this.app = app;
        this.password = password;
        this.creation_date = creation_date;
    }
}