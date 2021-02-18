package com.iit.oop.backend.premierleague.controller;

import com.iit.oop.backend.premierleague.model.HackedObjectInputStream;
import com.iit.oop.backend.premierleague.model.Match;
import com.iit.oop.backend.premierleague.model.SportsClub;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


@RestController
public class SportClubController {

    @Value("${sportclub}")
    private String fileName;

    public ArrayList<SportsClub> readAndLoadSportClubData() throws IOException, ClassNotFoundException {
        //de serialize
        FileInputStream fileInputStream = new FileInputStream(fileName);
        HackedObjectInputStream objectInputStream = new HackedObjectInputStream(fileInputStream);
        ArrayList<SportsClub> clubs = (ArrayList<SportsClub>) objectInputStream.readObject();
        objectInputStream.close();

        return clubs;
    }

    @CrossOrigin
    @RequestMapping("/clubDetails")
    public JSONArray clubDetails() throws IOException, ClassNotFoundException {
        ArrayList<SportsClub> clubs = readAndLoadSportClubData();
        JSONArray jsonArray = new JSONArray(); //json array

        for (SportsClub sportsClub : clubs) {
            JSONObject obj = new JSONObject(); //create json object
            //put values for json object like as key value pare
            obj.put("ClubName", sportsClub.getClubName());
            obj.put("Wins", sportsClub.getStatistic().get("Wins"));
            obj.put("Draws", sportsClub.getStatistic().get("Draws"));
            obj.put("Defeats", sportsClub.getStatistic().get("Defeats"));
            obj.put("GoalsSco", sportsClub.getStatistic().get("GoalsSco"));
            obj.put("GoalsRece", sportsClub.getStatistic().get("GoalsRece"));
            obj.put("Points", sportsClub.getStatistic().get("Points"));
            obj.put("Matches", sportsClub.getStatistic().get("Matches"));
            obj.put("Seasons", sportsClub.getStatistic().get("Seasons"));

            jsonArray.add(obj);


        }

        return jsonArray;
    }


    @CrossOrigin
    @RequestMapping("/sortByPoints")
    public JSONArray sortByPoints() throws IOException, ClassNotFoundException {
        ArrayList<SportsClub> clubs = readAndLoadSportClubData();
        JSONArray jsonArray = new JSONArray(); //json array
        //sort points
        Collections.sort(clubs, new Comparator<SportsClub>() {
            @Override
            public int compare(SportsClub o1, SportsClub o2) {
                return o2.getStatistic().get("Points").compareTo(o1.getStatistic().get("Points"));
            }
        });


        for (SportsClub sportsClub : clubs) {
            JSONObject obj = new JSONObject(); //create json object
            //put values for json object like as key value pair
            obj.put("ClubName", sportsClub.getClubName());
            obj.put("Wins", sportsClub.getStatistic().get("Wins"));
            obj.put("Draws", sportsClub.getStatistic().get("Draws"));
            obj.put("Defeats", sportsClub.getStatistic().get("Defeats"));
            obj.put("GoalsSco", sportsClub.getStatistic().get("GoalsSco"));
            obj.put("GoalsRece", sportsClub.getStatistic().get("GoalsRece"));
            obj.put("Points", sportsClub.getStatistic().get("Points"));
            obj.put("Matches", sportsClub.getStatistic().get("Matches"));
            obj.put("Seasons", sportsClub.getStatistic().get("Seasons"));

            jsonArray.add(obj); //add json array


        }

        return jsonArray;
    }

    @CrossOrigin
    @RequestMapping("/sortByGoalsScore")
    public JSONArray sortByGoalsScore() throws IOException, ClassNotFoundException {
        //sort goals score
        ArrayList<SportsClub> clubs = readAndLoadSportClubData();
        JSONArray jsonArray = new JSONArray(); //json array
        Collections.sort(clubs, new Comparator<SportsClub>() {
            @Override
            public int compare(SportsClub o1, SportsClub o2) {
                return o2.getStatistic().get("GoalsSco").compareTo(o1.getStatistic().get("GoalsSco"));
            }
        });


        for (SportsClub sportsClub : clubs) {
            JSONObject obj = new JSONObject(); //create json object
            //put values for json object like as key value pair
            obj.put("ClubName", sportsClub.getClubName());
            obj.put("Wins", sportsClub.getStatistic().get("Wins"));
            obj.put("Draws", sportsClub.getStatistic().get("Draws"));
            obj.put("Defeats", sportsClub.getStatistic().get("Defeats"));
            obj.put("GoalsSco", sportsClub.getStatistic().get("GoalsSco"));
            obj.put("GoalsRece", sportsClub.getStatistic().get("GoalsRece"));
            obj.put("Points", sportsClub.getStatistic().get("Points"));
            obj.put("Matches", sportsClub.getStatistic().get("Matches"));
            obj.put("Seasons", sportsClub.getStatistic().get("Seasons"));

            jsonArray.add(obj); //add json array

        }

        return jsonArray;
    }

    @CrossOrigin
    @RequestMapping("/sortByWins")
    public JSONArray sortByWins() throws IOException, ClassNotFoundException {
        //sort wins
        ArrayList<SportsClub> clubs = readAndLoadSportClubData();
        JSONArray jsonArray = new JSONArray(); //json array
        Collections.sort(clubs, new Comparator<SportsClub>() {
            @Override
            public int compare(SportsClub o1, SportsClub o2) {
                return o2.getStatistic().get("Wins").compareTo(o1.getStatistic().get("Wins"));
            }
        });


        for (SportsClub sportsClub : clubs) {
            JSONObject obj = new JSONObject(); //create json object
            //put values for json object like as key value pair
            obj.put("ClubName", sportsClub.getClubName());
            obj.put("Wins", sportsClub.getStatistic().get("Wins"));
            obj.put("Draws", sportsClub.getStatistic().get("Draws"));
            obj.put("Defeats", sportsClub.getStatistic().get("Defeats"));
            obj.put("GoalsSco", sportsClub.getStatistic().get("GoalsSco"));
            obj.put("GoalsRece", sportsClub.getStatistic().get("GoalsRece"));
            obj.put("Points", sportsClub.getStatistic().get("Points"));
            obj.put("Matches", sportsClub.getStatistic().get("Matches"));
            obj.put("Seasons", sportsClub.getStatistic().get("Seasons"));

            jsonArray.add(obj); //add json array

        }

        return jsonArray;
    }


}

