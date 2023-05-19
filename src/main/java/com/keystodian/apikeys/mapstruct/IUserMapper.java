package com.keystodian.apikeys.mapstruct;

import com.keystodian.apikeys.expose.dto.UserResponse;
import com.keystodian.apikeys.persistence.entities.User;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface IUserMapper {

    /**
     * Target (objetivo)  hace referencia al atributo de la clase donde se guarda el dato
     * Source (fuente)    hace referencia al atributo de la clase de la que se pilla el dato
     */
    @Mappings({

            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "roles", source = "roles")
    })
    UserResponse mapToDto(User user);
}
