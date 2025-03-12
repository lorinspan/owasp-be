package com.owasp.owaspbe.remotecodeexecution.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
@CrossOrigin("*") // Oricine poate accesa API-ul din orice origine
public class RCEController {

    @PostMapping("/execute")
    public ResponseEntity<Map<String, String>> executeCommand(@RequestBody CommandRequest request) {
        return ResponseEntity.ok(runShellCommand(request.getCommand()));
    }

    private Map<String, String> runShellCommand(String command) {
        Map<String, String> response = new HashMap<>();

        try {
            Process process = Runtime.getRuntime().exec(command); // Executa orice comanda, fara protectie
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            response.put("output", output.toString().trim());
        } catch (Exception e) {
            response.put("output", "Error executing command!");
        }

        return response;
    }

    static class CommandRequest {
        private String command;

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }
    }
}
