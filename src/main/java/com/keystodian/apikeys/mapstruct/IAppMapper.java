package com.keystodian.apikeys.mapstruct;

import com.keystodian.apikeys.expose.dto.dtoApp.AppResponse;
import com.keystodian.apikeys.persistence.entities.App;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IAppMapper {


    /**
     * Target (objetivo)  hace referencia al atributo de la clase donde se guarda el dato
     * Source (fuente)    hace referencia al atributo de la clase de la que se pilla el dato
     */

    @Mappings({
            @Mapping(target = "app", source = "app"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "creation_date", source = "creation_date")
    })
    AppResponse mapToDto(App app);
}
