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
    private final AppRepository appRepository;

    @GetMapping
    public ResponseEntity<List<AppResponse>> getAll(){
        return new ResponseEntity<List<AppResponse>>(passwordService.findAll(), HttpStatus.OK);
    }

    //Para comprobar lo que se guarda en ENTIDAD
//    @GetMapping("/{app}")
//    public ResponseEntity<Password>find(@PathVariable String app){
//        return ResponseEntity.status(HttpStatus.OK).body(passwordRepository.findById(app)
//                .orElseThrow(() -> new AppNotFoundException(app)));
//    }

    @GetMapping("/{app}")
    public ResponseEntity<AppResponse>findById(@PathVariable String app){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findByApp(app));
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
