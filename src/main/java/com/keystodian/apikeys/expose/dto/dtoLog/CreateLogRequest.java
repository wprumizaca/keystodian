package com.keystodian.apikeys.expose.dto.dtoLog;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateLogRequest {

    @NonNull
    private String logged_ip;

    @NotNull
    private Long id;

}
