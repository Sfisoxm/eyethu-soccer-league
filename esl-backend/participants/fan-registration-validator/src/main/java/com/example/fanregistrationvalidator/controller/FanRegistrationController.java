package com.example.fanregistrationvalidator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fan")
public class FanRegistrationController {
    @PostMapping("/register")
    public ResponseEntity<String> registerFan(@RequestBody String fan) {
        // Placeholder for fan validation logic
        return ResponseEntity.ok("Fan registration received and validated.");
    }
}
