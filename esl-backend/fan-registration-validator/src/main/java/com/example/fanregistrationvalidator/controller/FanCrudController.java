package com.example.fanregistrationvalidator.controller;

import com.example.fanregistrationvalidator.entity.Fan;
import com.example.fanregistrationvalidator.repository.FanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/fans")
public class FanCrudController {
    private final FanRepository fanRepository;

    public FanCrudController(FanRepository fanRepository) {
        this.fanRepository = fanRepository;
    }

    @PostMapping
    public Fan createFan(@RequestBody Fan fan) {
        return fanRepository.save(fan);
    }

    @GetMapping
    public List<Fan> getAllFans() {
        return fanRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fan> getFanById(@PathVariable UUID id) {
        Optional<Fan> fan = fanRepository.findById(id);
        return fan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fan> updateFan(@PathVariable UUID id, @RequestBody Fan updatedFan) {
        return fanRepository.findById(id)
                .map(fan -> {
                    fan.setName(updatedFan.getName());
                    fan.setEmail(updatedFan.getEmail());
                    return ResponseEntity.ok(fanRepository.save(fan));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFan(@PathVariable UUID id) {
        if (fanRepository.existsById(id)) {
            fanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
