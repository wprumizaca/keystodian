package com.keystodian.apikeys.persistence.repository;

import com.keystodian.apikeys.persistence.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LogRepository extends JpaRepository<Log, LocalDateTime> {
}
