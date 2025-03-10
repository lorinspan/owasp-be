package com.owasp.owaspbe.cryptographicfailures.repository;

import com.owasp.owaspbe.cryptographicfailures.model.CFUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CFUserRepository extends JpaRepository<CFUser, Long> {
    Optional<CFUser> findByUsernameAndPassword(String username, String password);
}
