package com.owasp.owaspbe.crosssitescripting.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XSSFeedbackService {

    private final JdbcTemplate jdbcTemplate;

    public XSSFeedbackService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ✅ Salvăm input-ul utilizatorului fără filtrare -> STORABLE XSS
    public void saveFeedback(String name, String message) {
        String sql = "INSERT INTO feedback (name, message) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, message);
    }

    // ✅ Returnăm datele direct fără protecție XSS
    public List<Map<String, Object>> getAllFeedback() {
        return jdbcTemplate.queryForList("SELECT name, message FROM feedback");
    }
}
