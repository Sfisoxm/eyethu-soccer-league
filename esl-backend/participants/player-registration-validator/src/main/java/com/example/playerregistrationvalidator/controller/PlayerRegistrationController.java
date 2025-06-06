package com.example.playerregistrationvalidator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerRegistrationController {
    @PostMapping("/register")
    public ResponseEntity<String> registerPlayer(@RequestBody String player) {
        // Placeholder for player validation logic
        return ResponseEntity.ok("Player registration received and validated.");
    }
}
