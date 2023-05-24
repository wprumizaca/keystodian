package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserEmailDTO;
import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserPasswDTO;
import com.keystodian.apikeys.expose.dto.dtoPassword.CreatePasswordRequest;
import com.keystodian.apikeys.expose.dto.dtoPassword.PasswordResponse;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.Password;
import com.keystodian.apikeys.persistence.entities.User;

import java.util.List;

public interface IPasswordService {

    List<PasswordResponse> findAll();

    PasswordResponse findByApp(String app);

    PasswordResponse saveApp(CreatePasswordRequest createPasswordRequest);

    void deleteByApp(String app);

    void delete(Password password);
}
