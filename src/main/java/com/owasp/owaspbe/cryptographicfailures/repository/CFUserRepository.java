package com.owasp.owaspbe.cryptographicfailures.repository;

import com.owasp.owaspbe.cryptographicfailures.model.CFUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CFUserRepository extends JpaRepository<CFUser, Long> {
    Optional<CFUser> findByUsername(String username);
}
