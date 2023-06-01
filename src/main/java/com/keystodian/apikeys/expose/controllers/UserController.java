package com.keystodian.apikeys.expose.controllers;


import com.keystodian.apikeys.exceptions.IdUserNotFoundException;
import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserEmailDTO;
import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserPasswDTO;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.UserRepository;
import com.keystodian.apikeys.services.impl.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return new ResponseEntity<List<UserResponse>>(userService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse>findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }



    @PutMapping("/email/{id}")
    public ResponseEntity<UserResponse> updateEmail(@RequestBody UpdateUserEmailDTO updateUserEmailDTO, @PathVariable Long id){
        return new ResponseEntity<UserResponse>(userService.editEmail(updateUserEmailDTO,id), HttpStatus.OK);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody UpdateUserPasswDTO updateUserPasswDTO , @PathVariable Long id){
        return new ResponseEntity<UserResponse>(userService.editPassw(updateUserPasswDTO,id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
