package com.eyethu.proposals.repository;

import com.eyethu.proposals.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByParticipantAndOption_Proposal_Id(String participant, Long proposalId);
}
