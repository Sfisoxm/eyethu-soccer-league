package com.example.sponsorregistrationvalidator.controller;

import com.example.sponsorregistrationvalidator.entity.Sponsor;
import com.example.sponsorregistrationvalidator.repository.SponsorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorCrudController {
    private final SponsorRepository sponsorRepository;

    public SponsorCrudController(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @PostMapping
    public Sponsor createSponsor(@RequestBody Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    @GetMapping
    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable UUID id) {
        Optional<Sponsor> sponsor = sponsorRepository.findById(id);
        return sponsor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable UUID id, @RequestBody Sponsor updatedSponsor) {
        return sponsorRepository.findById(id)
                .map(sponsor -> {
                    sponsor.setName(updatedSponsor.getName());
                    sponsor.setEmail(updatedSponsor.getEmail());
                    return ResponseEntity.ok(sponsorRepository.save(sponsor));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable UUID id) {
        if (sponsorRepository.existsById(id)) {
            sponsorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
