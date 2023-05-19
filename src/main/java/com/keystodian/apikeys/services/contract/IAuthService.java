package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.expose.dto.dtoUser.CreateUserRequest;
import com.keystodian.apikeys.expose.dto.dtoUser.UserResponse;
import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.entities.UserRole;

import java.util.Optional;
import java.util.Set;

public interface IAuthService {

    Optional<User> findByUser(String username);

    UserResponse save(CreateUserRequest createUserDTO, Set<UserRole> roles);


}
