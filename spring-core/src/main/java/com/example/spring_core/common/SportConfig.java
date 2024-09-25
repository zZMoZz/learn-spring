package com.example.spring_core.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // this annotation mark the class as bean factory
public class SportConfig {
    @Bean // this annotation mean that this method return a bean
    public Coach swimCoach() { // bean id of it, is the method name
        return new SwimCoach();
    }
}
