package com.example.spring_security_mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration // indicate this class contains bean definitions
public class DemoSecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // I create variable to can configure jdbc manually unlike below function.
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // retrieve user credentials tables
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    /*
    @Bean // indicate that returned object will be manage by spring
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Jdbc implements `UserDetailsManager` interface.
        // it will connect to provided dataSource during authentication process.
        return new JdbcUserDetailsManager(dataSource);
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // "throw Exception": indicate this method may throw an exception.
        // "http": used to configure security.
        // "SecurityFilterChain": represents security filters will be applied to requests, such as authentication type.

        // the method that used in configuration
        http.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers("/").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers("/leaders/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // means any request to the app must be authenticated.
            )
            .formLogin( form -> // create a custom login page
                form
                        .loginPage("/showLoginPage")  // endpoint that redirect if user try to access protected resource
                        .loginProcessingUrl("/login") // endpoint that redirect when login form submitted
                        .permitAll()                  // you can change the name of it. only reflect that in html page.

            )
            .logout(logout -> logout.permitAll() // add logout support to the app
            )
            .exceptionHandling(configure ->
                configure
                         .accessDeniedPage("/access-denied")
            );

        // finalize HttpSecurity configuration and returns a SecurityFilterChain object.
        return http.build();
    }

    /*
    @Bean // indicate returned object will manage by spring
    public InMemoryUserDetailsManager userDetailsManager() {
        // define users
        UserDetails amr = User.builder()
                .username("amr")
                .password("{noop}amr123")
                .roles("EMPLOYEE")
                .build();

        UserDetails ahmed = User.builder()
                .username("ahmed")
                .password("{noop}ahmed123")
                .roles("MANAGER")
                .build();

        UserDetails mona = User.builder()
                .username("mona")
                .password("{noop}mona123")
                .roles("ADMIN")
                .build();

        // this built-in class permit to define users in memory.
        return new InMemoryUserDetailsManager(amr, ahmed, mona);
    }
    */
}
