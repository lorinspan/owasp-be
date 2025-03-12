package com.owasp.owaspbe.serversiderequestforgery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin("*") // Oricine poate accesa API-ul din orice origine
public class SSRFController {

    private final RestTemplate restTemplate;

    public SSRFController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PostMapping("/check-stock")
    public ResponseEntity<Map<String, String>> checkStock(@RequestBody Map<String, String> request) {
        String storeUrl = request.get("url");
        Map<String, String> response = new HashMap<>();

        storeUrl = storeUrl.toLowerCase().replace("www.", "");

        // Hardcodarea unor magazine pentru simulare
        if (storeUrl.contains("emag")) {
            response.put("message", "Iphone 13 Mini: 10 bucati, Purificatoare Dyson: 5 bucati");
        } else if (storeUrl.contains("media-galaxy")) {
            response.put("message", "Iphone 13 Mini: 3 bucati, Purificatoare Dyson: 8 bucati");
        } else if (storeUrl.contains("altex")) {
            response.put("message", "Iphone 13 Mini: 6 bucati, Purificatoare Dyson: 2 bucati");
        } else if (storeUrl.contains("cel")) {
            response.put("message", "Iphone 13 Mini: 12 bucati, Purificatoare Dyson: 15 bucati");
        } else if (storeUrl.contains("avstore")) {
            response.put("message", "Iphone 13 Mini: 7 bucati, Purificatoare Dyson: 4 bucati");
        } else { // Daca URL-ul nu este in lista de mai sus, se face cerere HTTP directa
            response.put("message", restTemplate.getForObject(storeUrl, String.class));
        }

        return ResponseEntity.ok(response);
    }

}
