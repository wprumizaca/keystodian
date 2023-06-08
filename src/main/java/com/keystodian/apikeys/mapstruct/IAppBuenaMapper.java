package com.keystodian.apikeys.mapstruct;

import com.keystodian.apikeys.expose.dto.dtoAppBuena.AppBuenaResponse;
import com.keystodian.apikeys.persistence.entities.AppBuena;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IAppBuenaMapper {


    /**
     * Target (objetivo)  hace referencia al atributo de la clase donde se guarda el dato
     * Source (fuente)    hace referencia al atributo de la clase de la que se pilla el dato
     */

    @Mappings({
            @Mapping(target = "plataforma", source = "claveprimaria.plataforma"),
            @Mapping(target = "usuario", source = "claveprimaria.usuario"),
            @Mapping(target = "password", source = "password")
    })
    AppBuenaResponse mapToDto(AppBuena appBuena);
}
