package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.expose.dto.dtoLog.CreateLogRequest;
import com.keystodian.apikeys.expose.dto.dtoLog.LogResponse;
import com.keystodian.apikeys.expose.dto.dtoUser.CreateUserRequest;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.Log;
import com.keystodian.apikeys.persistence.entities.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ILogService {

    List<LogResponse> findAll();

    LogResponse findById(LocalDateTime log_time);

    LogResponse save(CreateLogRequest createLogRequest);

}
