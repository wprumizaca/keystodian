package com.keystodian.apikeys.expose.controllers;

import com.keystodian.apikeys.exceptions.RefreshTokenException;
import com.keystodian.apikeys.expose.dto.RefreshTokenRequest;
import com.keystodian.apikeys.expose.dto.dtoLogin.LoginResponse;
import com.keystodian.apikeys.expose.dto.dtoLogin.LoginRequest;
import com.keystodian.apikeys.expose.dto.dtoUser.CreateUserRequest;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.RefreshToken;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.services.impl.AuthService;
import com.keystodian.apikeys.services.impl.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenService refreshTokenService;


    /**
     * REGISTER
     */

    @PostMapping("/register/master")
    public ResponseEntity<UserResponse> save_master(@RequestBody CreateUserRequest newuser){
        return new ResponseEntity<UserResponse>(authService.saveMaster(newuser), HttpStatus.CREATED);

    }

    @PostMapping("/register/admin-user")
    public ResponseEntity<UserResponse> save_admin_user(@RequestBody CreateUserRequest newuser){
        return new ResponseEntity<UserResponse>(authService.saveAdminUser(newuser), HttpStatus.CREATED);

    }

    @PostMapping("/register/admin-app")
    public ResponseEntity<UserResponse> save_admin_app(@RequestBody CreateUserRequest newuser){
        return new ResponseEntity<UserResponse>(authService.saveAdminApp(newuser), HttpStatus.CREATED);

    }

    @PostMapping("/register/client")
    public ResponseEntity<UserResponse> save_client(@RequestBody CreateUserRequest newuser){
        return new ResponseEntity<UserResponse>(authService.saveClient(newuser), HttpStatus.CREATED);
    }


    /**
     LOGIN
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authenticationDTO = null;
        try {
            //Añadimos el usuario y contraseña del usuario que se va a loguear para, con estos datos, posteriormente Autenticarse
            authenticationDTO = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        }catch (BadCredentialsException ex){

            throw ex;

        }

        /**
         * Invoca a UserDetailsService para sacar de BBDD
         *  1. Nombre de Usuario
         */
        Authentication authentication = authManager.authenticate(authenticationDTO);
        User user = (User) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(authentication);

        refreshTokenService.delete(user); //Si tiene algun token de refresco lo borramo
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user); //Creamos token de refresco

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(user.getUsername());
        loginResponse.setRoles(user.getRoles().stream().map(String::valueOf)
                .toList());

        loginResponse.setToken(token);
        loginResponse.setRefreshToken(refreshToken.getToken());


        return ResponseEntity.status(HttpStatus.OK).body(loginResponse); //Se puede cambiar por CREATED

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify) //verificamos token refresh. verify es un método creado por nosotros
                .map(RefreshToken::getUser)//Si verify OK: obtenemos usuario
                .map(user -> {
                    //2. aquí GENERAMOS el token desde user
                    String token = jwtTokenProvider.generateToken(user);

                    refreshTokenService.delete(user); //refresh token antiguo lo borramos
                    RefreshToken refreshToken2 = refreshTokenService.createRefreshToken(user);


                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(LoginResponse.builder()
                                    .token(token)
                                    .refreshToken(refreshToken2.getToken())
                                    .build()
                            );

                })
                .orElseThrow(() -> new RefreshTokenException("Token de refresco No Encontrado"));
    }

}
