package com.keystodian.apikeys.expose.dto.dtoPassword;

import java.time.LocalDateTime;
import java.util.Set;

public record PasswordResponse(String app, String password, LocalDateTime creation_date) {


}