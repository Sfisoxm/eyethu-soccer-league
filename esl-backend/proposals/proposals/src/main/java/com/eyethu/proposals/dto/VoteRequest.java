package com.eyethu.proposals.dto;

public class VoteRequest {
    private String participant;
    private Long optionId;

    public String getParticipant() { return participant; }
    public void setParticipant(String participant) { this.participant = participant; }
    public Long getOptionId() { return optionId; }
    public void setOptionId(Long optionId) { this.optionId = optionId; }
}
