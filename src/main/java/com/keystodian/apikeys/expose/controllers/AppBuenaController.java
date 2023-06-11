package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.exceptions.AppCREATEDException;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.AppBuenaResponse;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.CreateAppBuenaRequest;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.UpdateAppBuenaContraseniaDTO;
import com.keystodian.apikeys.services.impl.AppBuenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/apps")
public class AppBuenaController {

    private final AppBuenaService passwordService;


    @GetMapping
    public ResponseEntity<List<AppBuenaResponse>> getAll(){
        List<AppBuenaResponse> appBuenaResponseList = passwordService.findAll();

        if (appBuenaResponseList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return new ResponseEntity<List<AppBuenaResponse>>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/{app}")
    public ResponseEntity<AppBuenaResponse>findById(@PathVariable String app){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findByApp(app));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AppBuenaResponse>>findById(@PathVariable Long id){

        List<AppBuenaResponse> appBuenaResponseList = passwordService.findByIdUser(id);

        if(appBuenaResponseList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findByIdUser(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AppBuenaResponse> save_master(@RequestBody CreateAppBuenaRequest newuser){

        passwordService.saveApp(newuser);

//        return ResponseEntity.status(HttpStatus.CREATED).build();
       return new ResponseEntity<AppBuenaResponse>(HttpStatus.CREATED);

    }

    @PutMapping("/editpassword/{id}")
    public ResponseEntity<AppBuenaResponse> updatePassword(@RequestBody UpdateAppBuenaContraseniaDTO updateAppBuenaContraseniaDTO, @PathVariable Long id){

        passwordService.editContraseña(updateAppBuenaContraseniaDTO,id);
        return new ResponseEntity<AppBuenaResponse>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{app}")
    public ResponseEntity<Void> deleteById(@PathVariable String app){
        passwordService.deleteByApp(app);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
