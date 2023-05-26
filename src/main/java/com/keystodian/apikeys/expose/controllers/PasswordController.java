package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserPasswDTO;
import com.keystodian.apikeys.expose.dto.dtoPassword.CreatePasswordRequest;
import com.keystodian.apikeys.expose.dto.dtoPassword.PasswordResponse;
import com.keystodian.apikeys.expose.dto.dtoPassword.UpdateContraseniaDTO;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.repository.PasswordRepository;
import com.keystodian.apikeys.services.impl.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/passwords")
public class PasswordController {

    private final PasswordService passwordService;
    private final PasswordRepository passwordRepository;

    @GetMapping
    public ResponseEntity<List<PasswordResponse>> getAll(){
        return new ResponseEntity<List<PasswordResponse>>(passwordService.findAll(), HttpStatus.OK);
    }

    //Para comprobar lo que se guarda en ENTIDAD
//    @GetMapping("/{app}")
//    public ResponseEntity<Password>find(@PathVariable String app){
//        return ResponseEntity.status(HttpStatus.OK).body(passwordRepository.findById(app)
//                .orElseThrow(() -> new AppNotFoundException(app)));
//    }

    @GetMapping("/{app}")
    public ResponseEntity<PasswordResponse>findById(@PathVariable String app){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findByApp(app));
    }


    @PostMapping("/register")
    public ResponseEntity<PasswordResponse> save_master(@RequestBody CreatePasswordRequest newuser){
        return new ResponseEntity<PasswordResponse>(passwordService.saveApp(newuser), HttpStatus.CREATED);

    }

    @PutMapping("/{app}")
    public ResponseEntity<PasswordResponse> updatePassword(@RequestBody UpdateContraseniaDTO updateContraseniaDTO, @PathVariable String app){
        return new ResponseEntity<PasswordResponse>(passwordService.editContraseña(updateContraseniaDTO,app), HttpStatus.OK);
    }


    @DeleteMapping("/{app}")
    public ResponseEntity<Void> deleteById(@PathVariable String app){
        passwordService.deleteByApp(app);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
