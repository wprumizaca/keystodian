package com.keystodian.apikeys.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        cors(Customizer.withDefaults())
        http
                .cors(Customizer.withDefaults())
                .csrf().disable()//Deshabilitar sesion. Aqui estamos usando tokens (sin control de estados)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//deshabilitamos estados. Esta APi es sin estado y sin sesión

        //Aqui se configuran todas las urls de los controladores
        http.authorizeHttpRequests()
                //EndPoints Públicos
                .mvcMatchers("/auth/**","/users/**","/logs/**","/apps/**", "/appsBuena/**")
                .permitAll() //Habilitamos controladores.//Autorizamos Controladores. En este caso controlador (/auth/**).  Esto sirve para que se pueda Autenticar cualquiera (EVIDENTE).

                .anyRequest().authenticated()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true).permitAll()
        ;

        return  http.build(); //una vez tratado el objeto lo devolvemos
    }
}
