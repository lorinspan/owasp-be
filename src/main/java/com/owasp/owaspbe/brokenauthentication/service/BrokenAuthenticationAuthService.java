package com.owasp.owaspbe.brokenauthentication.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrokenAuthenticationAuthService {

    private final JdbcTemplate jdbcTemplate;

    public BrokenAuthenticationAuthService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> validateUser(String username, String password) { // Vulnerabil la SQL Injection
        String sql = "SELECT * FROM ba_users WHERE username = '" + username + "' AND password = '" + password + "'";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        if (!results.isEmpty()) {
            Map<String, Object> user = results.get(0);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("userId", user.get("id"));
            response.put("username", user.get("username"));
            response.put("token", "insecure-token-" + user.get("id")); // Token nesecurizat
            return response;
        } else {
            return Map.of("status", "failed");
        }
    }
}
