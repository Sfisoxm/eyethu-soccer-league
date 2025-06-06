package com.eyethu.proposals.model;

import jakarta.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getParticipant() { return participant; }
    public void setParticipant(String participant) { this.participant = participant; }
    public Option getOption() { return option; }
    public void setOption(Option option) { this.option = option; }
}
