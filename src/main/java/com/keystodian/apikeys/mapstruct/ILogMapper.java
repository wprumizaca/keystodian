package com.keystodian.apikeys.mapstruct;


import com.keystodian.apikeys.expose.dto.dtoLog.CreateLogRequest;
import com.keystodian.apikeys.expose.dto.dtoLog.LogResponse;
import com.keystodian.apikeys.persistence.entities.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ILogMapper {

    /**
     * Target (objetivo)  hace referencia al atributo de la clase donde se guarda el dato
     * Source (fuente)    hace referencia al atributo de la clase de la que se pilla el dato
     */

    @Mappings({
            @Mapping(target = "logged_ip", source = "logged_ip"),
            @Mapping(target = "user.id", source = "id")

    })
    Log mapToEntity(CreateLogRequest createLogRequest);

    @Mappings({
            @Mapping(target = "logged_ip", source = "logged_ip"),
            @Mapping(target = "id", source = "user.id")

    })
    LogResponse mapToDto(Log log);
}
