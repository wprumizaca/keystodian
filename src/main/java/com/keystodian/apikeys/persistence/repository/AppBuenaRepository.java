package com.keystodian.apikeys.persistence.repository;

import com.keystodian.apikeys.persistence.entities.AppBuena;
import com.keystodian.apikeys.persistence.entities.ClaveCompuestaAppBuena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppBuenaRepository extends JpaRepository<AppBuena, ClaveCompuestaAppBuena> {
    List<AppBuena> findByUserId(Long id);

    AppBuena findById(ClaveCompuestaAppBuena clave);

}
