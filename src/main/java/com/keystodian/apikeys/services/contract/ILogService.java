package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.expose.dto.dtoLog.CreateLogRequest;
import com.keystodian.apikeys.expose.dto.dtoLog.LogResponse;

import java.util.List;

public interface ILogService {

    List<LogResponse> findAll();

    LogResponse findById(String log_time);

    LogResponse save(CreateLogRequest createLogRequest);

}
