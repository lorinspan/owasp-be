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
            return ResponseEntity.ok(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    public void deleteUser(Long id) { // Nu verifica daca user-ul e admin cand sterge alt user
        userRepository.deleteById(id);
    }

    public List<BACUser> getAllUsers() { // Orice utilizator poate vedea toti userii
        return userRepository.findAll();
    }

    public BACUser getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }


    public BACUser updateUser(Long id, BACUser updatedUser) {
        BACUser user = userRepository.findById(id).orElseThrow();
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole()); // Permite schimbarea propriului rol
        return userRepository.save(user);
    }
}
