package com.example.teamregistrationvalidator.controller;

import com.example.teamregistrationvalidator.entity.Team;
import com.example.teamregistrationvalidator.repository.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
public class TeamCrudController {
    private final TeamRepository teamRepository;

    public TeamCrudController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable UUID id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable UUID id, @RequestBody Team updatedTeam) {
        return teamRepository.findById(id)
                .map(team -> {
                    team.setName(updatedTeam.getName());
                    team.setCoachId(updatedTeam.getCoachId());
                    team.setVenue(updatedTeam.getVenue());
                    team.setTransport(updatedTeam.getTransport());
                    team.setStatus(updatedTeam.getStatus());
                    return ResponseEntity.ok(teamRepository.save(team));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
