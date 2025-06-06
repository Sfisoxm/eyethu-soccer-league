package com.example.teamregistrationvalidator.controller;

import com.example.teamregistrationvalidator.model.Team;
import com.example.teamregistrationvalidator.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/teams")
public class TeamRegistrationController {

    @PostMapping("/register")
    public ResponseEntity<?> registerTeam(@RequestBody Team team) {
        // Validate 20 unique players
        if (team.getPlayers() == null || team.getPlayers().size() != 20) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team must have exactly 20 players.");
        }
        Set<String> playerIds = new HashSet<>();
        for (Player player : team.getPlayers()) {
            if (!playerIds.add(player.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player IDs must be unique.");
            }
        }
        // Validate coach
        if (team.getCoach() == null || team.getCoach().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach account is required.");
        }
        // Validate venue
        if (team.getVenue() == null || team.getVenue().getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue details are required.");
        }
        // Validate transport
        if (team.getTransport() == null || team.getTransport().getType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transport details are required.");
        }
        // Mark as pending
        team.setStatus("pending");
        // In a real app, save to DB and notify admin for approval
        return ResponseEntity.ok(team);
    }
}
