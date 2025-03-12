package com.owasp.owaspbe.pathtraversal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@CrossOrigin("*") // Oricine poate accesa API-ul din orice origine
public class FileReadController {

    private static final String BASE_PATH = "src/main/resources/recipes/";

    @PostMapping("/read")
    public ResponseEntity<Map<String, String>> readFile(@RequestBody FileRequest request) {
        try {
            String filePath = BASE_PATH + request.getPath(); // Nu exista validare
            String content = Files.readString(Paths.get(filePath)); // Citeste orice fișier
            return ResponseEntity.ok(Map.of("message", content));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error reading file!"));
        }
    }

    static class FileRequest {
        private String path;
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
    }
}
