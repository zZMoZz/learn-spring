package com.example.spring_core.rest;

import com.example.spring_core.common.Coach;
import com.example.spring_core.common.Player;
import com.example.spring_core.common.Refree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // variables that hold dependencies
    private Coach coach;
    private Player player1;
    private Player player2;
    @Autowired
    private Refree refree; // Field Injection

    // Dependency Injection for coach (constructor Injection)
    @Autowired // tells spring to inject dependency automatically (can neglect in this case)
    public DemoController(@Qualifier("cricketCoach") Coach coach) { // handle multiple dependencies
        System.out.println("Track demoController when work");       // cricketCoach initialized because it is injected even it lazy.
        this.coach = coach;                                         // don't forget to try SwimCoach
    }

    // Dependency Injection for coach (setter Injection)
    @Autowired // tells spring to inject dependency automatically
    public void setPlayer(Player player1, Player player2) { // we handled multiple dependencies using @Primary
        this.player1 = player1; // we create two instance to test bean scope
        this.player2 = player2; // try to change TennisPlayer scope to prototype
                                // and see equality between these two variables
    }

    // Main page
    @GetMapping("/")
    public String getMainPage() {
        return "Hello Everyone";
    }

    // Use the coach
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }

    // Use the player
    @GetMapping("/playerjob")
    public String getPlayerJob() {
        return player1.playerJob();
    }

    // Use the referee
    @GetMapping("/refreejob")
    public String getRefreeRole() {
        return refree.getRefreeRole();
    }
    // Check two players point to same instance or not
    @GetMapping("/check")
    public boolean check() {
            return player1 == player2;

    }
}
