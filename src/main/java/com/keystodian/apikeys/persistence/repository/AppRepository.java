package com.keystodian.apikeys.persistence.repository;

import com.keystodian.apikeys.persistence.entities.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<App, String> {
}
