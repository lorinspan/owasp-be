package com.owasp.owaspbe.brokenauthentication.controller;

import com.owasp.owaspbe.brokenauthentication.service.BrokenAuthenticationAuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ba-auth")
@CrossOrigin("*") // Oricine poate accesa API-ul din orice origine
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
