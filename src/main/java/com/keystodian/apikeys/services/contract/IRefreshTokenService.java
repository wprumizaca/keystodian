package com.keystodian.apikeys.services.contract;

import com.keystodian.apikeys.persistence.entities.RefreshToken;
import com.keystodian.apikeys.persistence.entities.User;

import java.util.Optional;

public interface IRefreshTokenService {

    RefreshToken createRefreshToken(User user);

    Optional<RefreshToken> findByToken(String token);


    int delete(User user);

    RefreshToken verify(RefreshToken refreshToken);
}
