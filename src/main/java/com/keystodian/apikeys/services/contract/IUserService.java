package com.keystodian.apikeys.services.contract;


import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserEmailDTO;
import com.keystodian.apikeys.expose.dto.dtoCambios.UpdateUserPasswDTO;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.User;

import java.util.List;

public interface IUserService {
    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse editEmail(UpdateUserEmailDTO updateUserEmailDTO, Long id);

    UserResponse editPassw(UpdateUserPasswDTO updateUserPasswDTO, Long id);

    void deleteById(Long id);

    void delete(User user);

}
