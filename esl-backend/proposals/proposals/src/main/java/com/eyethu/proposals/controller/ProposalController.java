package com.eyethu.proposals.controller;

import com.eyethu.proposals.dto.ProposalRequest;
import com.eyethu.proposals.dto.VoteRequest;
import com.eyethu.proposals.model.Option;
import com.eyethu.proposals.model.Proposal;
import com.eyethu.proposals.model.Vote;
import com.eyethu.proposals.repository.OptionRepository;
import com.eyethu.proposals.repository.ProposalRepository;
import com.eyethu.proposals.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {
    
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private VoteRepository voteRepository;

    @PostMapping
    public ResponseEntity<?> createProposal(@RequestBody ProposalRequest request) {
        if (request.getOptions() == null || request.getOptions().size() != 3) {
            return ResponseEntity.badRequest().body("Exactly 3 options required");
        }
        Proposal proposal = new Proposal();
        proposal.setTitle(request.getTitle());
        proposal.setDescription(request.getDescription());
        proposal = proposalRepository.save(proposal);
        for (String opt : request.getOptions()) {
            Option option = new Option();
            option.setValue(opt);
            option.setProposal(proposal);
            optionRepository.save(option);
        }
        return ResponseEntity.ok(proposalRepository.findById(proposal.getId()));
    }

    @GetMapping
    public List<Proposal> getAllProposals() {
        return proposalRepository.findAll();
    }

    @PostMapping("/{proposalId}/vote")
    public ResponseEntity<?> vote(@PathVariable Long proposalId, @RequestBody VoteRequest request) {
        if (voteRepository.existsByParticipantAndOption_Proposal_Id(request.getParticipant(), proposalId)) {
            return ResponseEntity.badRequest().body("Participant has already voted for this proposal");
        }
        Optional<Option> optionOpt = optionRepository.findById(request.getOptionId());
        if (optionOpt.isEmpty() || !optionOpt.get().getProposal().getId().equals(proposalId)) {
            return ResponseEntity.badRequest().body("Option not found for this proposal");
        }
        Vote vote = new Vote();
        vote.setParticipant(request.getParticipant());
        vote.setOption(optionOpt.get());
        voteRepository.save(vote);
        return ResponseEntity.ok("Vote recorded");
    }

    @GetMapping("/{proposalId}/official")
    public ResponseEntity<?> getOfficialOption(@PathVariable Long proposalId) {
        Optional<Proposal> proposalOpt = proposalRepository.findById(proposalId);
        if (proposalOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Proposal proposal = proposalOpt.get();
        Option official = proposal.getOptions().stream()
                .max(Comparator.comparingInt(o -> o.getVotes() != null ? o.getVotes().size() : 0))
                .orElse(null);
        if (official == null) {
            return ResponseEntity.badRequest().body("No options found");
        }
        return ResponseEntity.ok(official);
    }
}
