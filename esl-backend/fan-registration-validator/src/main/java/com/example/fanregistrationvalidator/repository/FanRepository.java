package com.example.fanregistrationvalidator.repository;

import com.example.fanregistrationvalidator.entity.Fan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FanRepository extends JpaRepository<Fan, UUID> {
}
