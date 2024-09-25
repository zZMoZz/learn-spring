package com.example.spring_core.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
    // for track initialization process of this class
    public TrackCoach() {
        System.out.println("I'm" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
