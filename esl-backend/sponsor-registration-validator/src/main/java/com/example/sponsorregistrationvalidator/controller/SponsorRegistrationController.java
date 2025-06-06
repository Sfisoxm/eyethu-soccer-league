package com.example.sponsorregistrationvalidator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sponsor")
public class SponsorRegistrationController {
    @PostMapping("/register")
    public ResponseEntity<String> registerSponsor(@RequestBody String sponsor) {
        // Placeholder for sponsor validation logic
        return ResponseEntity.ok("Sponsor registration received and validated.");
    }
}
