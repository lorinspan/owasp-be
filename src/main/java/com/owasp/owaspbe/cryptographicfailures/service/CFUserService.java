package com.owasp.owaspbe.cryptographicfailures.service;

import com.owasp.owaspbe.cryptographicfailures.model.CFUser;
import com.owasp.owaspbe.cryptographicfailures.repository.CFUserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CFUserService {
    private final CFUserRepository userRepository;

    public CFUserService(CFUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CFUser registerUser(String username, String password, String email) {
        String hashedPassword = hashPassword(password); // ❌ Folosim MD5 fără salting
        CFUser user = new CFUser(username, hashedPassword, email);
        return userRepository.save(user);
    }

    public CFUser authenticate(String username, String password) {
        String hashedPassword = hashPassword(password);
        Optional<CFUser> user = userRepository.findByUsernameAndPassword(username, hashedPassword);
        return user.orElse(null);
    }

    public CFUser updateEmail(Long id, String newEmail) {
        Optional<CFUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            CFUser existingUser = user.get();
            existingUser.setEmail(newEmail);
            return userRepository.save(existingUser); // ❌ Nu verificăm dacă userul are permisiuni!
        }
        return null;
    }

    public String hashPassword(String password) {
        return DigestUtils.md5Hex(password); // ❌ Vulnerabil la rainbow table attacks
    }
}
