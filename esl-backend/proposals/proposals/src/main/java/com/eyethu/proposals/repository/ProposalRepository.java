package com.eyethu.proposals.repository;

import com.eyethu.proposals.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
