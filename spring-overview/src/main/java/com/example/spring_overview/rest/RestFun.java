package com.example.spring_overview.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestFun {
    // Inject my custom properties
    @Value("${leader.name}")
    private String leaderName;

    @Value("${team.name}")
    private String teamName;

    // home page
    @GetMapping("/")
    public String getHomePage() {
        return "Hello on my website";
    }

    // show custom properties
    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        return "Team: " + teamName + " | " + "Leader: " + leaderName;
    }
}
