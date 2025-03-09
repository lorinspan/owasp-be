package com.owasp.owaspbe.crosssitescripting.controller;

import com.owasp.owaspbe.crosssitescripting.service.XSSFeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*") // Permite orice origine (vulnerabil la atacuri CORS)
public class XSSFeedbackController {

    private final XSSFeedbackService feedbackService;

    public XSSFeedbackController(XSSFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // ✅ Endpoint vulnerabil la Stored XSS
    @PostMapping("/submit")
    public Map<String, String> submitFeedback(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String message = request.get("message");
        feedbackService.saveFeedback(name, message);

        // Returnăm un JSON valid
        return Map.of("message", "Feedback submitted successfully!");
    }


    // ✅ Endpoint care expune XSS - afișează mesajele nesecurizate
    @GetMapping("/list")
    public List<Map<String, Object>> getFeedback() {
        return feedbackService.getAllFeedback();
    }
}
