package com.keystodian.apikeys.mapstruct;

import com.keystodian.apikeys.expose.dto.dtoPassword.PasswordResponse;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.Password;
import com.keystodian.apikeys.persistence.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IPasswordMapper {


    /**
     * Target (objetivo)  hace referencia al atributo de la clase donde se guarda el dato
     * Source (fuente)    hace referencia al atributo de la clase de la que se pilla el dato
     */

    @Mappings({
            @Mapping(target = "app", source = "app"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "creation_date", source = "creation_date")
    })
    PasswordResponse mapToDto(Password password);
}
