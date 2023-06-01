package com.keystodian.apikeys.services.impl;

import com.keystodian.apikeys.exceptions.AppNotFoundException;
import com.keystodian.apikeys.exceptions.NombreExistenteException;
import com.keystodian.apikeys.expose.dto.dtoApp.AppResponse;
import com.keystodian.apikeys.expose.dto.dtoApp.CreateAppRequest;
import com.keystodian.apikeys.expose.dto.dtoApp.UpdateContraseniaDTO;
import com.keystodian.apikeys.mapstruct.IAppMapper;
import com.keystodian.apikeys.persistence.entities.App;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.AppRepository;
import com.keystodian.apikeys.services.contract.IAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppService implements IAppService {

    private final AppRepository appRepository;
    private final IAppMapper iAppMapper;


    @Override
    public List<AppResponse> findAll() {
        return appRepository.findAll()
                .stream()
                .map(password -> iAppMapper.mapToDto(password))
                .collect(Collectors.toList());
    }

    @Override
    public AppResponse findByApp(String app) {

        App password = appRepository.findById(app).orElseThrow(()-> new AppNotFoundException(app));

        return  iAppMapper.mapToDto(password); //devuelve el DTO
    }

    @Override
    public AppResponse saveApp(CreateAppRequest createAppRequest) {
        User user = new User();
        user.setId(createAppRequest.getId());

        App app = new App();
        app.setUser(user);
        app.setApp(createAppRequest.getApp());
        app.setPassword(createAppRequest.getPassword());
        app.setCreation_date(LocalDateTime.now());
        try {
            appRepository.save(app);
            AppResponse getUserDTO = iAppMapper.mapToDto(app);
            return getUserDTO;

        } catch (DataIntegrityViolationException ex) { //Se usa para el Nombre de usuario (unique=true)
            throw  new NombreExistenteException(); //CAMBIAR A APPEXISTENT!
        }
    }

    @Override
    public AppResponse editContraseña(UpdateContraseniaDTO updateContraseniaDTO, String app) {

        App password = appRepository.findById(app).orElseThrow(()-> new AppNotFoundException(app));

        password.setPassword(updateContraseniaDTO.getPassword());
        appRepository.save(password);

        return iAppMapper.mapToDto(password);
    }


    @Override
    public void deleteByApp(String app) {

        if(appRepository.existsById(app)){
            appRepository.deleteById(app);
        }else{
            throw new AppNotFoundException(app);

        }

    }

    @Override
    public void delete(App app) {deleteByApp(app.getApp());}
}
