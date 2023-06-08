package com.keystodian.apikeys.services.impl;

import com.keystodian.apikeys.exceptions.AppNotFoundException;
import com.keystodian.apikeys.exceptions.IdUserNotFoundException;
import com.keystodian.apikeys.exceptions.NombreExistenteException;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.AppBuenaResponse;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.CreateAppBuenaRequest;
import com.keystodian.apikeys.expose.dto.dtoAppBuena.UpdateAppBuenaContraseniaDTO;
import com.keystodian.apikeys.mapstruct.IAppBuenaMapper;
import com.keystodian.apikeys.persistence.entities.AppBuena;
import com.keystodian.apikeys.persistence.entities.ClaveCompuestaAppBuena;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.AppBuenaRepository;
import com.keystodian.apikeys.persistence.repository.UserRepository;
import com.keystodian.apikeys.security.PasswordUtils;
import com.keystodian.apikeys.services.contract.IAppBuenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppBuenaService implements IAppBuenaService {

    private final AppBuenaRepository appBuenaRepository;
    private final IAppBuenaMapper iAppBuenaMapper;
    private final UserRepository userRepository;


    @Override
    public List<AppBuenaResponse> findAll() {

        return appBuenaRepository.findAll()
                .stream()
                .map(password -> iAppBuenaMapper.mapToDto(password))
                .collect(Collectors.toList());
    }



    @Override
    public AppBuenaResponse findByApp(ClaveCompuestaAppBuena clave) {

        AppBuena password = appBuenaRepository.findById(clave);

        return  iAppBuenaMapper.mapToDto(password); //devuelve el DTO
    }

    @Override
    public List<AppBuenaResponse> findByIdUser(Long id) {
        if(userRepository.existsById(id)){

            return appBuenaRepository.findByUserId(id)
                            .stream()
                                    .map(app -> iAppBuenaMapper.mapToDto(app))
                    .collect(Collectors.toList());

        }else{
            throw new IdUserNotFoundException(id);
        }
    }

    @Override
    public AppBuenaResponse saveApp(CreateAppBuenaRequest createAppBuenaRequest) {

        AppBuena appBuena = new AppBuena();

        ClaveCompuestaAppBuena claveprimaria = new ClaveCompuestaAppBuena();
        claveprimaria.setPlataforma(createAppBuenaRequest.getPlataforma());
        claveprimaria.setUsuario(createAppBuenaRequest.getUsuario());
        claveprimaria.setId(createAppBuenaRequest.getId());

        appBuena.setClaveprimaria(claveprimaria);

        String encryptedPassword = PasswordUtils.encryptPassword(createAppBuenaRequest.getPassword());
        appBuena.setPassword(encryptedPassword);
//        appBuena.setCreation_date(LocalDateTime.now());
        try {
            appBuenaRepository.save(appBuena);
            AppBuenaResponse getAppDTO = iAppBuenaMapper.mapToDto(appBuena);

            String decryptPassword = PasswordUtils.decryptPassword(getAppDTO.getPassword());
            getAppDTO.setPassword(decryptPassword);

            return getAppDTO;

        } catch (DataIntegrityViolationException ex) { //Se usa para el Nombre de usuario (unique=true)
            throw  new NombreExistenteException(); //CAMBIAR A APPEXISTENT!
        }
    }

//    @Override
//    public AppBuenaResponse editContraseña(UpdateAppBuenaContraseniaDTO updateAppBuenaContraseniaDTO, String app) {
//
//        AppBuena password = appBuenaRepository.findById(app).orElseThrow(()-> new AppNotFoundException(app));
//
//        password.setPassword(updateAppBuenaContraseniaDTO.getPassword());
//        appBuenaRepository.save(password);
//
//        return iAppBuenaMapper.mapToDto(password);
//    }


//    @Override
//    public void deleteByApp(String app) {
//
//        if(appBuenaRepository.existsById(app)){
//            appBuenaRepository.deleteById(app);
//        }else{
//            throw new AppNotFoundException(app);
//
//        }
//
//    }

//    @Override
//    public void delete(AppBuena app) {deleteByApp(app.getApp());}
}
