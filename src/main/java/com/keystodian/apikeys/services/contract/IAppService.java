package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.expose.dto.dtoApp.AppResponse;
import com.keystodian.apikeys.expose.dto.dtoApp.CreateAppRequest;
import com.keystodian.apikeys.expose.dto.dtoApp.UpdateContraseniaDTO;
import com.keystodian.apikeys.persistence.entities.App;


import java.util.List;

public interface IAppService {

    List<AppResponse> findAll();

    AppResponse findByApp(String app);

    List<AppResponse> findByIdUser(Long id);

    AppResponse saveApp(CreateAppRequest createAppRequest);

    AppResponse editContraseña(UpdateContraseniaDTO updateContraseniaDTO, String app);

    void deleteByApp(String app);

    void delete(App app);
}
