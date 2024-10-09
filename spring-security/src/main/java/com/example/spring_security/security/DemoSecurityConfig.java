package com.example.spring_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration // mark the class as source of bean definitions
public class DemoSecurityConfig {

    // Define user credentials information using custom tables (db)
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // this class responsible for retrieving user credentials details for db
        // takes db that will connect with
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define custom sql query to fetch user details.
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        // define custom sql query to fetch roles.
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        // return configured
        return jdbcUserDetailsManager;
    }

    /* Define user credentials information using default tables (db)

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // `DataSource`: injected by spring, represents database connection.
        // `UserDetailsManager`: interface to load user data from database.

        // It is a class that implements `UserDetailsManager` interface.
        return new JdbcUserDetailsManager(dataSource);
    }

    */


    /* Define user credentials information in-memory

           // make spring manage lifecycle of returned object
    @Bean  // allowing returned object to handle authentication using users in memory
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        // Define users
        UserDetails mona = User.builder()  // 'User' class used to define the user
                .username("mona")          // 'builder()' used for creating user
                .password("{noop}mona123") // 'build()' completes user creation & returns a 'UserDetails' object.
                .roles("EMPLOYEE")
                .build();

        UserDetails mai = User.builder()
                .username("mai")
                .password("{noop}mai123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails hala = User.builder()
                .username("hala")
                .password("{noop}hala123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        // this is built-in class in spring security that stores users details in memory
        // and which uses to manage authentication.
        return new InMemoryUserDetailsManager(mona, mai, hala);
    }
    */
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // `HttpSecurity`: this object allows to configure web-based security for specific HTTP requests.
        http.authorizeHttpRequests(configurer ->
                configurer // `requestMatchers()`: matches the request with allowed roles
                        .requestMatchers(HttpMethod.GET, "/api/employees")
                        .hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**")
                        .hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees")
                        .hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees")
                        .hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**")
                        .hasRole("ADMIN"));

        // Use HTTP basic authentication
        http.httpBasic(Customizer.withDefaults()); // this method applies the basic configuration for HTTP Basic Authentication.

        // Disable CSRF
        http.csrf(csrf -> csrf.disable());

        // Finalize `HttpSecurity` configuration and returns `SecurityFilterChain` object
        return http.build();
    }

}
