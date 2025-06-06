package com.example.sponsorregistrationvalidator.repository;

import com.example.sponsorregistrationvalidator.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, UUID> {
}
