package com.example.spring_core.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy // this bean will not initialize when spring boot app starts, only when summoned.
public class BaseballCoach implements Coach {
    // for track initialization process of this class
    public BaseballCoach() {
        System.out.println("I'm" + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 min in batting practice";
    }
}
