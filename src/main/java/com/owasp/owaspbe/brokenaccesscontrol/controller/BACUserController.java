package com.owasp.owaspbe.brokenaccesscontrol.controller;

import com.owasp.owaspbe.brokenaccesscontrol.model.BACUser;
import com.owasp.owaspbe.brokenaccesscontrol.service.BACUserService;
import com.owasp.owaspbe.util.CryptoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bac")
@CrossOrigin("*") // Oricine poate accesa API-ul din orice origine
public class BACUserController {

    private final BACUserService userService;

    public BACUserController(BACUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<BACUser> login(@RequestBody Map<String, String> request) {
        return userService.authenticate(request.get("username"), request.get("password"));
    }

    @GetMapping("/users")
    public List<BACUser> getAllUsers() { // Orice utilizator poate accesa aceasta lista
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public BACUser getUserById(@PathVariable Long id) { // Oricine poate accesa orice user
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public BACUser updateUser(@PathVariable Long id, @RequestBody BACUser user) { // Orice utilizator poate edita orice user
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) { // Nu verifica permisiunile, oricine poate sterge orice user
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @GetMapping("/admin/config")
    public Map<String, String> getAdminConfig() { // Oricine poate primi datele critice ale proiectului / mediului
        Map<String, String> config = new HashMap<>();
        config.put("debug", "true");
        config.put("logLevel", "TRACE");
        config.put("encryptionKey", CryptoUtil.getSecretKey());
        config.put("databaseUrl", "jdbc:postgresql://localhost:5432/owasp");
        return config;
    }


}
