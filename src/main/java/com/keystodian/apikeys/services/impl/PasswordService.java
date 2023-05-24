package com.keystodian.apikeys.services.impl;

import com.keystodian.apikeys.expose.dto.dtoPassword.PasswordResponse;
import com.keystodian.apikeys.persistence.entities.Password;
import com.keystodian.apikeys.services.contract.IPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordService implements IPasswordService {


    @Override
    public List<PasswordResponse> findAll() {
        return null;
    }

    @Override
    public PasswordResponse findByApp(String app) {
        return null;
    }

    @Override
    public void deleteByApp(String app) {

    }

    @Override
    public void delete(Password password) {

    }
}
