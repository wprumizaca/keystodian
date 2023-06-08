package com.keystodian.apikeys.persistence.repository;

import com.keystodian.apikeys.persistence.entities.AppBuena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppBuenaRepository extends JpaRepository<AppBuena, String> {
    List<AppBuena> findByUserId(Long id);



}
