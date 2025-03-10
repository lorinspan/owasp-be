package com.owasp.owaspbe.cryptographicfailures.service;

import com.owasp.owaspbe.cryptographicfailures.model.CFUser;
import com.owasp.owaspbe.cryptographicfailures.repository.CFUserRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class CFUserService {
    private final CFUserRepository userRepository;

    public CFUserService(CFUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CFUser registerUser(String username, String password, String email) {
        String hashedPassword = hashPassword(password);
        CFUser user = new CFUser(username, hashedPassword, email);
        return userRepository.save(user);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // ❌ MD5 este vulnerabil la atacuri de coliziune
            byte[] hashBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 hashing error", e);
        }
    } // NU ARE SALTING IMPLEMENTAT SI DE ACEEA DACA DOI USERI AU ACEEASI PAROLA SE POATE GHICI UTILIZAND RAINBOW ATTACK
}
