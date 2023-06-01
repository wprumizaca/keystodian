package com.keystodian.apikeys.expose.dto.dtoApp;

import java.time.LocalDateTime;

public record AppResponse(String app, String password, LocalDateTime creation_date) {


}