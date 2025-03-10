package com.owasp.owaspbe.brokenaccesscontrol.service;

import com.owasp.owaspbe.brokenaccesscontrol.model.BACUser;
import com.owasp.owaspbe.brokenaccesscontrol.repository.BACUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BACUserService {
    private final BACUserRepository userRepository;

    public BACUserService(BACUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<BACUser> authenticate(String username, String password) {
        Optional<BACUser> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok(user.get()); // ✅ 200 OK dacă autentificarea reușește
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"); // ❌ 401 Unauthorized dacă autentificarea eșuează
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id); // 🚨 Nu verifică dacă user-ul e admin sau dacă șterge alt user
    }

    public List<BACUser> getAllUsers() {
        return userRepository.findAll(); // ❌ Orice utilizator poate vedea toți userii
    }

    public BACUser getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }


    public BACUser updateUser(Long id, BACUser updatedUser) {
        BACUser user = userRepository.findById(id).orElseThrow();
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole()); // ❌ Permite schimbarea propriului rol!
        return userRepository.save(user);
    }
}
