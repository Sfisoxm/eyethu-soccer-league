package com.example.coachregistrationvalidator.controller;

import com.example.coachregistrationvalidator.entity.Coach;
import com.example.coachregistrationvalidator.repository.CoachRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/coaches")
public class CoachCrudController {
    private final CoachRepository coachRepository;

    public CoachCrudController(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @PostMapping
    public Coach createCoach(@RequestBody Coach coach) {
        return coachRepository.save(coach);
    }

    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable UUID id) {
        Optional<Coach> coach = coachRepository.findById(id);
        return coach.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable UUID id, @RequestBody Coach updatedCoach) {
        return coachRepository.findById(id)
                .map(coach -> {
                    coach.setName(updatedCoach.getName());
                    coach.setEmail(updatedCoach.getEmail());
                    return ResponseEntity.ok(coachRepository.save(coach));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable UUID id) {
        if (coachRepository.existsById(id)) {
            coachRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
