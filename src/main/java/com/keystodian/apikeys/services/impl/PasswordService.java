package com.keystodian.apikeys.services.impl;

import com.keystodian.apikeys.exceptions.AppNotFoundException;
import com.keystodian.apikeys.exceptions.NombreExistenteException;
import com.keystodian.apikeys.expose.dto.dtoPassword.CreatePasswordRequest;
import com.keystodian.apikeys.expose.dto.dtoPassword.PasswordResponse;
import com.keystodian.apikeys.mapstruct.IPasswordMapper;
import com.keystodian.apikeys.persistence.entities.Password;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.PasswordRepository;
import com.keystodian.apikeys.services.contract.IPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasswordService implements IPasswordService {

    private final PasswordRepository passwordRepository;
    private final IPasswordMapper iPasswordMapper;


    @Override
    public List<PasswordResponse> findAll() {
        return passwordRepository.findAll()
                .stream()
                .map(password -> iPasswordMapper.mapToDto(password))
                .collect(Collectors.toList());
    }

    @Override
    public PasswordResponse findByApp(String app) {

        Password password = passwordRepository.findById(app).orElseThrow(()-> new AppNotFoundException(app));

        return  iPasswordMapper.mapToDto(password); //devuelve el DTO
    }

    @Override
    public PasswordResponse saveApp(CreatePasswordRequest createPasswordRequest) {
        User user = new User();
        user.setId(createPasswordRequest.getId());

        Password password = new Password();
        password.setUser(user);
        password.setApp(createPasswordRequest.getApp());
        password.setPassword(createPasswordRequest.getPassword());

        try {
            passwordRepository.save(password);
            PasswordResponse getUserDTO = iPasswordMapper.mapToDto(password);
            return getUserDTO;

        } catch (DataIntegrityViolationException ex) { //Se usa para el Nombre de usuario (unique=true)
            throw  new NombreExistenteException(); //CAMBIAR A APPEXISTENT!
        }
    }




    @Override
    public void deleteByApp(String app) {

        if(passwordRepository.existsById(app)){
            passwordRepository.deleteById(app);
        }else{
            throw new AppNotFoundException(app);

        }

    }

    @Override
    public void delete(Password password) {deleteByApp(password.getApp());}
}
