package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.expose.dto.dtoAppBuena.AppBuenaResponse;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.CreateAppBuenaRequest;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.UpdateAppBuenaContraseniaDTO;
import com.keystodian.apikeys.persistence.entities.AppBuena;

import java.util.List;

public interface IAppBuenaService {

    List<AppBuenaResponse> findAll();

    AppBuenaResponse findByApp(String app);

    List<AppBuenaResponse> findByIdUser(Long id);

    AppBuenaResponse saveApp(CreateAppBuenaRequest createAppBuenaRequest);

    AppBuenaResponse editContraseña(UpdateAppBuenaContraseniaDTO updateAppBuenaContraseniaDTO, Long id);

    void deleteByApp(String app);

//    void delete(AppBuena app);
}
