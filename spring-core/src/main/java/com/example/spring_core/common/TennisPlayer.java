package com.example.spring_core.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Primary // this class will be the default class for all players
public class TennisPlayer implements Player {
    @Override
    public String playerJob() {
        return "I'm a player tennis";
    }
}
