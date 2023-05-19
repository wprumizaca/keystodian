package com.keystodian.apikeys.services.impl;

import com.keystodian.apikeys.persistence.entities.User;
import com.keystodian.apikeys.persistence.repository.UserRepository;
import com.keystodian.apikeys.services.contract.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
