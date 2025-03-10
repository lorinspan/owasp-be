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
@CrossOrigin("*") // ❌ Oricine poate accesa API-ul din orice origine
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
    public List<BACUser> getAllUsers() {
        return userService.getAllUsers(); // ❌ Orice utilizator poate accesa această listă
    }

    @GetMapping("/users/{id}")
    public BACUser getUserById(@PathVariable Long id) {
        return userService.getUserById(id); // ✅ Oricine poate accesa orice user!
    }

    @PutMapping("/users/{id}")
    public BACUser updateUser(@PathVariable Long id, @RequestBody BACUser user) {
        return userService.updateUser(id, user); // ❌ Orice utilizator poate edita orice user
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id); // 🚨 Nu verifică permisiunile, oricine poate șterge orice user
        return ResponseEntity.ok("User deleted successfully!");
    }

    @GetMapping("/admin/config")
    public Map<String, String> getAdminConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("debug", "true"); // ❌ Debug activ
        config.put("logLevel", "TRACE"); // ❌ Nivelul logurilor este periculos
        config.put("encryptionKey", CryptoUtil.getSecretKey()); // ❌ Cheie hardcodată
        config.put("databaseUrl", "jdbc:postgresql://localhost:5432/owasp"); // ❌ Expune conexiunea la DB
        return config; // ✅ Oricine poate accesa aceste date critice
    }


}
