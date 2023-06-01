package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.exceptions.IdUserNotFoundException;
import com.keystodian.apikeys.exceptions.LogNotFoundException;
import com.keystodian.apikeys.expose.dto.dtoLog.CreateLogRequest;
import com.keystodian.apikeys.expose.dto.dtoLog.LogResponse;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.Log;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.LogRepository;
import com.keystodian.apikeys.services.impl.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/logs")
public class LogController {

    private final LogService logService;


    @GetMapping
    public ResponseEntity<List<LogResponse>> getAll(){
        return new ResponseEntity<List<LogResponse>>(logService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{log_time}")
    public ResponseEntity<LogResponse>find(@PathVariable LocalDateTime log_time){
        return ResponseEntity.status(HttpStatus.OK).body(logService.findById(log_time));
    }


    @PostMapping("/register")
    public ResponseEntity<LogResponse>save(@RequestBody CreateLogRequest createLogRequest){
        return ResponseEntity.status(HttpStatus.OK).body(logService.save(createLogRequest));
    }

}
