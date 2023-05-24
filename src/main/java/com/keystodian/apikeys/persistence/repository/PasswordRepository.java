package com.keystodian.apikeys.persistence.repository;

import com.keystodian.apikeys.persistence.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, String> {
}
