package com.example.coachregistrationvalidator.repository;

import com.example.coachregistrationvalidator.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoachRepository extends JpaRepository<Coach, UUID> {
}
