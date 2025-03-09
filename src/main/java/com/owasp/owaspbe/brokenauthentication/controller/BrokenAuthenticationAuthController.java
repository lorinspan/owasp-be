package com.owasp.owaspbe.brokenauthentication.controller;

import com.owasp.owaspbe.brokenauthentication.service.BrokenAuthenticationAuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ba-auth")
@CrossOrigin("*") // ❌ Permite orice origine, vulnerabil la CORS
public class BrokenAuthenticationAuthController {

    private final BrokenAuthenticationAuthService authService;

    public BrokenAuthenticationAuthController(BrokenAuthenticationAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        return authService.validateUser(username, password);
    }
}
