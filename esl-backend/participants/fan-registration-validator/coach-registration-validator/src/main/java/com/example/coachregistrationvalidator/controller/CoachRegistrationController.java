package com.example.coachregistrationvalidator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coach")
public class CoachRegistrationController {
    @PostMapping("/register")
    public ResponseEntity<String> registerCoach(@RequestBody String coach) {
        // Placeholder for coach validation logic
        return ResponseEntity.ok("Coach registration received and validated.");
    }
}
