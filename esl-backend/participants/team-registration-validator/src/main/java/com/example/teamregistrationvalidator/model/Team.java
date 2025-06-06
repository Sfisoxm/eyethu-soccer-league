package com.example.teamregistrationvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private String id;
    private String name;
    private Coach coach;
    private List<Player> players;
    private Venue venue;
    private Transport transport;
    private String status; // e.g., "pending", "approved"
}
