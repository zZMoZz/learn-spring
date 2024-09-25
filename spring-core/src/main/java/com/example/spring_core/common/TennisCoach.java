package com.example.spring_core.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    // for track initialization process of this class
    public TennisCoach() {
        System.out.println("I'm" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
