package com.example.spring_core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component // make the class as bean
public class CricketCoach implements Coach {
    // for track initialization process of this class
    public CricketCoach() {
        System.out.println("I'm" + getClass().getSimpleName());
    }

    // Initialization method
    @PostConstruct
    public void doStartupStuff() {
        System.out.println("Do somethings when the class start");
    }

    // Destruction method
    @PreDestroy
    public void doCleanupStuff() {
        System.out.println("Do somethings when the class destroyed");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 min";
    }
}
