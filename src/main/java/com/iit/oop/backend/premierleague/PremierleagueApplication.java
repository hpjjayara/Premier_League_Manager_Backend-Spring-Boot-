package com.iit.oop.backend.premierleague;

import com.iit.oop.backend.premierleague.model.Match;
import com.iit.oop.backend.premierleague.model.SchoolFootballClub;
import com.iit.oop.backend.premierleague.model.SportsClub;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class PremierleagueApplication {

    public static void main(String[] args) {
        SpringApplication.run(PremierleagueApplication.class, args);
    }
}
