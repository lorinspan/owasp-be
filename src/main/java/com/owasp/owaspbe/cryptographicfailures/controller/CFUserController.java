package com.owasp.owaspbe.cryptographicfailures.controller;

import com.owasp.owaspbe.cryptographicfailures.model.CFUser;
import com.owasp.owaspbe.cryptographicfailures.service.CFUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*") // ❌ Oricine poate accesa API-ul din orice origine
@RequestMapping("/api/cf")
public class CFUserController {
    private final CFUserService userService;

    public CFUserController(CFUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<CFUser> registerUser(@RequestBody CFUser user) {
        CFUser newUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<CFUser> login(@RequestBody Map<String, String> request) {
        CFUser user = userService.authenticate(request.get("username"), request.get("password"));
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build(); // ❌ Nu avem nicio măsură de protecție împotriva brute-force
    }

    // ❌ Endpoint vulnerabil: Oricine poate schimba email-ul oricărui utilizator
    @PutMapping("/users/{id}/email")
    public ResponseEntity<CFUser> updateEmail(@PathVariable Long id, @RequestBody Map<String, String> request) {
        CFUser updatedUser = userService.updateEmail(id, request.get("email"));
        return ResponseEntity.ok(updatedUser);
    }
}
