package com.keystodian.apikeys.persistence.repository;

import com.keystodian.apikeys.persistence.entities.RefreshToken;
import com.keystodian.apikeys.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, User> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying //para actualizacion y borrado
    int deleteByUser(User user); //Borrar un token introduciendo usuario. No se va a usar de momento
}
