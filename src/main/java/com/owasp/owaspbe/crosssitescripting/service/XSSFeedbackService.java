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

    public void saveFeedback(String name, String message) { // Salvam input-ul utilizatorului fara filtrare
        String sql = "INSERT INTO feedback (name, message) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, message);
    }

    public List<Map<String, Object>> getAllFeedback() { // Returnam datele direct fara protectie XSS
        return jdbcTemplate.queryForList("SELECT name, message FROM feedback");
    }
}
