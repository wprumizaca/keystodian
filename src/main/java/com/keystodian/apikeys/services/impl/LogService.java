package com.keystodian.apikeys.services.impl;

import com.keystodian.apikeys.exceptions.LogNotFoundException;
import com.keystodian.apikeys.exceptions.NombreExistenteException;
import com.keystodian.apikeys.expose.dto.dtoLog.CreateLogRequest;
import com.keystodian.apikeys.expose.dto.dtoLog.LogResponse;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.mapstruct.IAppMapper;
import com.keystodian.apikeys.mapstruct.ILogMapper;
import com.keystodian.apikeys.persistence.entities.Log;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.LogRepository;
import com.keystodian.apikeys.services.contract.ILogService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService implements ILogService {

    private final LogRepository logRepository;
    private final ILogMapper iLogMapper;

    @Override
    public List<LogResponse> findAll() {
        return logRepository.findAll()
                .stream()
                .map(log -> iLogMapper.mapToDto(log))
                .collect(Collectors.toList());
    }

    @Override
    public LogResponse findById(String log_time) {
//        Log log = logRepository.findById(log_time).orElseThrow(() -> new LogNotFoundException(log_time));
//        return iLogMapper.mapToDto(log);

//        String encodedLogTime = URLEncoder.encode(log_time, StandardCharsets.ISO_8859_1);
        LocalDateTime decodedLogTime = LocalDateTime.parse(log_time); // Asegúrate de utilizar el formato correcto para decodificar el valor

        Log log = logRepository.findById(decodedLogTime).orElseThrow(() -> new LogNotFoundException(decodedLogTime));
        return iLogMapper.mapToDto(log);

        //NO FUNCIONA
    }

    @Override
    public LogResponse save(CreateLogRequest createLogRequest) {
        User user = new User();
        user.setId(createLogRequest.getId());

        Log log = iLogMapper.mapToEntity(createLogRequest);
        log.setLog_time(LocalDateTime.now());

        logRepository.save(log);

        return iLogMapper.mapToDto(log);
    }
}
