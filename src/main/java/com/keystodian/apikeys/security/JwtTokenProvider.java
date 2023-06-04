package com.keystodian.apikeys.security;

import com.keystodian.apikeys.exceptions.JwtTokenException;
import com.keystodian.apikeys.persistence.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class JwtTokenProvider {

    Logger log = LoggerFactory.getLogger(getClass());

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.security.jwt.expiration}")
    private Long jwtDurationSeconds;


    /**
     Generamos TOKEN. Se prodrá hacer desde Authentication o desde User
     1...
     authentication.getPrincipal() -> tiene dentro
     el usuario que está intentado Login desde AuthController

     2... Este se crea por motivos del token de Refresco
     generateToken(User user)

     */
    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        //extraemos un usuario a partir del objeto authentication

        return generateToken(user);

    }

    public  String generateToken(User user){

        //OPCIÓN PARA CREAR TOKEN
        //A partir de un secreto ( jwtSecret.getBytes() ) que tenemos almacenado en el Properties + 1 algoritmo (HS512)->FIRMAMOS
        // vamos a firmar el contenido que añadimos (  user.getUsername(),  user.getEmail(), user.getId(),y una serie de fechas )
        //Resumen: esto es el PAYLOAD
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512) // firmar el contenido que añadimos con Algoritmo + secreto
                .setHeaderParam("typ","JWT")
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtDurationSeconds*1000)))
                .claim("id", user.getId())  //todavía no se usa.
                .claim("username", user.getUsername())
                .claim("mail", user.getEmail())


                .compact();
    }


    public boolean isValidToken(String token){

        if(!StringUtils.hasLength(token))
            return false;

        try{  //Creamos el validador de tokens que contiene nuestro secreto
            JwtParser validadortoken = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build();

            validadortoken.parseClaimsJws(token); //Si el token es válido DEVUELVE true

            return true;
        }catch(SignatureException e){
            log.info("Error en la firma del token", e);
            throw new JwtTokenException("Error en la firma del token");
        }catch(MalformedJwtException | UnsupportedJwtException e){
            log.info("Token incorrecto", e);
            throw new JwtTokenException("Token incorrecto");
        }catch(ExpiredJwtException e){
            log.info("Token expirado", e);
            throw new JwtTokenException("Token expirado");
        }

    }

    public String getUsernameFromToken(String token){
        JwtParser obtener = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build();

        Claims claims = obtener.parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }
}
