package com.owasp.owaspbe.crosssitescripting.controller;

import com.owasp.owaspbe.crosssitescripting.service.XSSFeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*") // Oricine poate accesa API-ul din orice origine
public class XSSFeedbackController {

    private final XSSFeedbackService feedbackService;

    public XSSFeedbackController(XSSFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/submit")
    public Map<String, String> submitFeedback(@RequestBody Map<String, String> request) { // Endpoint vulnerabil la Stored XSS
        String name = request.get("name");
        String message = request.get("message");
        feedbackService.saveFeedback(name, message);

        return Map.of("message", "Feedback submitted successfully!");
    }


    @GetMapping("/list")
    public List<Map<String, Object>> getFeedback() { // Endpoint care expune XSS - afiseaza mesajele nesecurizate
        return feedbackService.getAllFeedback();
    }
}
