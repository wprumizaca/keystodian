package com.keystodian.apikeys.expose.dto.dtoLog;

import java.time.LocalDateTime;

public record LogResponse(LocalDateTime log_time, String logged_ip, Long id) {


}