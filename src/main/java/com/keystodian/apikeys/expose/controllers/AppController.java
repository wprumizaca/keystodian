package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.expose.dto.dtoApp.AppResponse;
import com.keystodian.apikeys.expose.dto.dtoApp.CreateAppRequest;
import com.keystodian.apikeys.expose.dto.dtoApp.UpdateContraseniaDTO;
import com.keystodian.apikeys.persistence.repository.AppRepository;
import com.keystodian.apikeys.services.impl.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/apps")
public class AppController {

    private final AppService passwordService;


    @GetMapping
    public ResponseEntity<List<AppResponse>> getAll(){
        return new ResponseEntity<List<AppResponse>>(passwordService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{app}")
    public ResponseEntity<AppResponse>findById(@PathVariable String app){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findByApp(app));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AppResponse>>findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findByIdUser(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AppResponse> save_master(@RequestBody CreateAppRequest newuser){
        return new ResponseEntity<AppResponse>(passwordService.saveApp(newuser), HttpStatus.CREATED);

    }

    @PutMapping("/{app}")
    public ResponseEntity<AppResponse> updatePassword(@RequestBody UpdateContraseniaDTO updateContraseniaDTO, @PathVariable String app){
        return new ResponseEntity<AppResponse>(passwordService.editContraseña(updateContraseniaDTO,app), HttpStatus.OK);
    }


    @DeleteMapping("/{app}")
    public ResponseEntity<Void> deleteById(@PathVariable String app){
        passwordService.deleteByApp(app);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
