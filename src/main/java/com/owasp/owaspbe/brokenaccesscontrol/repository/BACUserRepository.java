package com.owasp.owaspbe.brokenaccesscontrol.repository;

import com.owasp.owaspbe.brokenaccesscontrol.model.BACUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BACUserRepository extends JpaRepository<BACUser, Long> {
    Optional<BACUser> findByUsername(String username);
}
