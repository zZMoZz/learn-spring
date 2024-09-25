package com.example.spring_core.common;

import org.springframework.stereotype.Component;

@Component
public class FootballRefree implements Refree {
    @Override
    public String getRefreeRole() {
        return "I referee football matches";
    }
}
