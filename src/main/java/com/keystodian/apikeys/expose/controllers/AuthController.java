package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.exceptions.UserNotFoundException;
import com.keystodian.apikeys.expose.dto.dtoLogin.LoginResponse;
import com.keystodian.apikeys.expose.dto.dtoLogin.LoginRequest;
import com.keystodian.apikeys.expose.dto.dtoUser.CreateUserRequest;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.UserRepository;
import com.keystodian.apikeys.services.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * REGISTER
     */

    @PostMapping("/register/master")
    public ResponseEntity<UserResponse> save_master(@RequestBody CreateUserRequest newuser){
        return new ResponseEntity<UserResponse>(authService.saveMaster(newuser), HttpStatus.CREATED);

    }




    /**
     LOGIN
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

            User user = userRepository.findByUsername(loginRequest.username());
            LoginResponse loginResponse = new LoginResponse();

            if(user == null){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            if (user != null) {
                if (passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
                    loginResponse.setId(user.getId());
                    loginResponse.setUsername(user.getUsername());
                    //Se puede cambiar por CREATED
                }else{
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

}
