package com.owasp.owaspbe.cryptographicfailures.service;

import com.owasp.owaspbe.cryptographicfailures.model.CFUser;
import com.owasp.owaspbe.cryptographicfailures.repository.CFUserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class CFUserService {
    private final CFUserRepository userRepository;

    public CFUserService(CFUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CFUser registerUser(String username, String password, String email) {
        String hashedPassword = hashPassword(password); // Stocăm parola ca MD5
        CFUser user = new CFUser(username, hashedPassword, email);
        return userRepository.save(user);
    }

    public String hashPassword(String password) {
        return DigestUtils.md5Hex(password); // ❌ MD5 este extrem de vulnerabil la atacuri rainbow table
    } // NU ARE SALTING IMPLEMENTAT SI DE ACEEA DACA DOI USERI AU ACEEASI PAROLA SE POATE GHICI UTILIZAND RAINBOW ATTACK
}
