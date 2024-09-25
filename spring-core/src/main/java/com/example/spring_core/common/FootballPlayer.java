package com.example.spring_core.common;

import org.springframework.stereotype.Component;

@Component
public class FootballPlayer implements Player {
    @Override
    public String playerJob() {
        return "I'm football player";
    }
}
