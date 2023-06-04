package com.keystodian.apikeys.persistence.repository;

import com.keystodian.apikeys.persistence.entities.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRepository extends JpaRepository<App, String> {


    List<App> findByUserId(Long id);
}
