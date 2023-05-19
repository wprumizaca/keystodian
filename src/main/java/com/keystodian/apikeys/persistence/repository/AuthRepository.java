package com.keystodian.apikeys.persistence.repository;
import com.keystodian.apikeys.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository de JPA
 */
@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
