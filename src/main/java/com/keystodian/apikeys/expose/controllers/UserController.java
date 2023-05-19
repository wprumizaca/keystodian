package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    ResponseEntity<?> get(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
