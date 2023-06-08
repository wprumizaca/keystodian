package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.expose.dto.dtoAppBuena.AppBuenaResponse;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.CreateAppBuenaRequest;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.UpdateAppBuenaContraseniaDTO;
import com.keystodian.apikeys.persistence.entities.AppBuena;
import com.keystodian.apikeys.persistence.entities.User;
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
        }
        return new ResponseEntity<List<AppBuenaResponse>>(passwordService.findAll(), HttpStatus.OK);
    }

//    @GetMapping("/{plataforma}/{usuario}/{id}")
//    public ResponseEntity<AppBuenaResponse>findById(@PathVariable String plataforma,@PathVariable String usuario,@PathVariable Long id){
//
//        AppBuena appBuena = new AppBuena();
//        appBuena.setPlataforma(plataforma);
//        appBuena.setUsuario(usuario);
//
//        User user = new User();
//        user.setId(id);
//
//        appBuena.setUser(user);
//
//        if (appBuena == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }else {
//            return ResponseEntity.status(HttpStatus.OK).body(passwordService.findByApp(appBuena));
//        }
//    }

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
        return new ResponseEntity<AppBuenaResponse>(passwordService.saveApp(newuser), HttpStatus.CREATED);

    }

//    @PutMapping("/{app}")
//    public ResponseEntity<AppBuenaResponse> updatePassword(@RequestBody UpdateAppBuenaContraseniaDTO updateAppBuenaContraseniaDTO, @PathVariable String app){
//        return new ResponseEntity<AppBuenaResponse>(passwordService.editContraseña(updateAppBuenaContraseniaDTO,app), HttpStatus.OK);
//    }


//    @DeleteMapping("/{app}")
//    public ResponseEntity<Void> deleteById(@PathVariable String app){
//        passwordService.deleteByApp(app);
//        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//    }

}
