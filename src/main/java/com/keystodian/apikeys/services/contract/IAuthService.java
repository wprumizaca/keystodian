package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.persistence.entities.User;

import java.util.List;

public interface IAuthService {

    List<User> getAll();

}
