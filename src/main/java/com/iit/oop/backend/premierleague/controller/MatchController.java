package com.iit.oop.backend.premierleague.controller;

import com.iit.oop.backend.premierleague.model.HackedObjectInputStream;
import com.iit.oop.backend.premierleague.model.Match;
import com.iit.oop.backend.premierleague.model.SportsClub;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class MatchController {

    @Autowired
    SportClubController sportClubController;
    @Value("${match}")
    private String fileName;


    @CrossOrigin
    @RequestMapping("/matchDetails")
    public JSONArray matchDetails() throws IOException, ClassNotFoundException {
        ArrayList<Match> matches = readAndLoadMatchData();
        JSONArray jsonArray = new JSONArray(); //json array

        for (Match match : matches) {
            JSONObject obj = new JSONObject(); //create json object
            //put values for json object like as key value pair
            obj.put("TeamOneName", match.getTeamOneName());
            obj.put("TeamTwoName", match.getTeamTwoName());
            obj.put("TeamOneGoalScore", match.getTeamOneScore());
            obj.put("TeamTwoGoalScore", match.getTeamTwoScore());
            obj.put("TeamOnePoints", match.getTeamOnePoint());
            obj.put("TeamTwoPoints", match.getTeamTwoPoint());
            obj.put("Date", match.getDateOfMatch().getDate());

            jsonArray.add(obj); //add json array


        }


        System.out.println("\nsportsClubsArrayList Object has been de-serialized. \n");
        return jsonArray;
    }

    private ArrayList<Match> readAndLoadMatchData() throws IOException, ClassNotFoundException {
        //de serialize
        FileInputStream fileInputStream = new FileInputStream(fileName);
        HackedObjectInputStream objectInputStream = new HackedObjectInputStream(fileInputStream);
        ArrayList<Match> matches = (ArrayList<Match>) objectInputStream.readObject();
        objectInputStream.close();

        return matches;

    }

    @CrossOrigin
    @RequestMapping("/sortByDate")
    public JSONArray sortByDate() throws IOException, ClassNotFoundException {
        ArrayList<Match> matches = readAndLoadMatchData();
        JSONArray jsonArray = new JSONArray(); //json array
        //sort day,month,year
        Collections.sort(matches, new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                if (o1.getDateOfMatch().getYear() > o2.getDateOfMatch().getYear()) {
                    return 1;
                } else if (o1.getDateOfMatch().getYear() < o2.getDateOfMatch().getYear()) {
                    return -1;
                } else {
                    if ((o1.getDateOfMatch().getMonth()) > o2.getDateOfMatch().getMonth()) {
                        return 1;
                    } else if (o1.getDateOfMatch().getMonth() < o2.getDateOfMatch().getMonth()) {
                        return -1;
                    } else {
                        if ((o1.getDateOfMatch().getDay() > o2.getDateOfMatch().getDay())) {
                            return 1;
                        } else if (o1.getDateOfMatch().getDay() < o2.getDateOfMatch().getDay()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        });


        for (Match match : matches) {
            JSONObject obj = new JSONObject(); //create json object
            //put values for json object like as key value pair
            obj.put("TeamOneName", match.getTeamOneName());
            obj.put("TeamTwoName", match.getTeamTwoName());
            obj.put("TeamOneGoalScore", match.getTeamOneScore());
            obj.put("TeamTwoGoalScore", match.getTeamTwoScore());
            obj.put("TeamOnePoints", match.getTeamOnePoint());
            obj.put("TeamTwoPoints", match.getTeamTwoPoint());
            obj.put("Date", match.getDateOfMatch().getDate());

            jsonArray.add(obj);//add json array


        }

        return jsonArray;
    }

    private void storeMatchDetails(ArrayList<Match> matches) {
        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Method for serialization of object
            objectOutputStream.writeObject(matches);

            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println(matches);

            System.out.println("matchesClubArrayList Object has been serialized\n");

        } catch (IOException ex) {
            System.out.println("IOException is caught\n");
        }

    }

    @CrossOrigin
    @RequestMapping("/addRandomMatch")
    public JSONArray addRandomMatch() throws IOException, ClassNotFoundException {
        ArrayList<Match> matches = readAndLoadMatchData();
        ArrayList<SportsClub> clubs = sportClubController.readAndLoadSportClubData();
        JSONArray jsonArray = new JSONArray(); //json array

        SportsClub updateClub1 = null;
        SportsClub updateClub2 = null;


        final int[] randomGenerateClubs = new Random().ints(1, clubs.size()).distinct().limit(2).toArray(); //generate two random different values between 1 and size of arrayList using int array
        SportsClub club1 = clubs.get(randomGenerateClubs[0]);
        SportsClub club2 = clubs.get(randomGenerateClubs[1]);
        //get random object in int array
        String name1 = club1.getClubName();
        String name2 = club2.getClubName();


        for (SportsClub sportsClub : clubs) {
            if (sportsClub.getClubName().equals(name1)) { //equals of in arrayList  club name and random name
                updateClub1 = sportsClub;
            }
        }

        for (SportsClub sportsClub : clubs) {
            if (sportsClub.getClubName().equals(name2)) { //equals of in arrayList  club name and random name
                updateClub2 = sportsClub;
            }
        }
        //get random values for goals score between 0 to 20
        int max = 20;
        int min = 0;
        int score1 = (int) (Math.random() * (max - min + 1) + min);
        int score2 = (int) (Math.random() * (max - min + 1) + min);

        int newGoalScore1 = updateClub1.getStatistic().get("GoalsSco") + score1;
        updateClub1.getStatistic().replace("GoalsSco", newGoalScore1); //replace hashMap by new values
        int newGoalScore2 = updateClub2.getStatistic().get("GoalsSco") + score2;
        updateClub2.getStatistic().replace("GoalsSco", newGoalScore2); //replace hashMap by new values

        int newGoalReceived1 = updateClub1.getStatistic().get("GoalsRece") + score2;
        updateClub1.getStatistic().replace("GoalsRece", newGoalReceived1); //replace hashMap by new values
        int newGoalReceived2 = updateClub2.getStatistic().get("GoalsRece") + score1;
        updateClub2.getStatistic().replace("GoalsRece", newGoalReceived2); //replace hashMap by new values

        int newMatch1 = updateClub1.getStatistic().get("Matches") + 1;
        updateClub1.getStatistic().replace("Matches", newMatch1); //replace hashMap by new values
        int newMatch2 = updateClub2.getStatistic().get("Matches") + 1;
        updateClub2.getStatistic().replace("Matches", newMatch2); //replace hashMap by new values

        int wins1;
        int wins2;
        int draws1;
        int draws2;
        int defeats1;
        int defeats2;
        int points1;
        int points2;

        if (score1 > score2) {
            wins1 = 1;
            draws1 = 0;
            defeats1 = 0;
            points1 = 3;
            wins2 = 0;
            draws2 = 0;
            defeats2 = 1;
            points2 = 0;

            int newWin1 = updateClub1.getStatistic().get("Wins") + wins1;
            updateClub1.getStatistic().replace("Wins", newWin1);
            int newPoint1 = updateClub1.getStatistic().get("Points") + points1;
            updateClub1.getStatistic().replace("Points", newPoint1);
            int newDefeat2 = updateClub2.getStatistic().get("Defeats") + defeats2;
            updateClub2.getStatistic().replace("Defeats", newDefeat2);

        } else if (score1 == score2) {
            wins1 = 0;
            draws1 = 1;
            defeats1 = 0;
            points1 = 1;
            wins2 = 0;
            draws2 = 1;
            defeats2 = 0;
            points2 = 1;

            int newDraw1 = updateClub1.getStatistic().get("Draws") + draws1;
            updateClub1.getStatistic().replace("Draws", newDraw1);
            int newPoint1 = updateClub1.getStatistic().get("Points") + points1;
            updateClub1.getStatistic().replace("Points", newPoint1);
            int newDarw2 = updateClub2.getStatistic().get("Draws") + defeats2;
            updateClub2.getStatistic().replace("Draws", newDarw2);
            int newPoint2 = updateClub2.getStatistic().get("Points") + points2;
            updateClub2.getStatistic().replace("Points", newPoint2);

        } else {
            wins1 = 0;
            draws1 = 0;
            defeats1 = 1;
            points1 = 0;
            wins2 = 1;
            draws2 = 0;
            defeats2 = 0;
            points2 = 3;

            int newWin2 = updateClub2.getStatistic().get("Wins") + wins2;
            updateClub2.getStatistic().replace("Wins", newWin2);
            int newPoint2 = updateClub2.getStatistic().get("Points") + points2;
            updateClub2.getStatistic().replace("Points", newPoint2);
            int newDefeat1 = updateClub1.getStatistic().get("Defeats") + defeats1;
            updateClub1.getStatistic().replace("Defeats", newDefeat1);
        }

        LocalDate localDate = LocalDate.now();
        int startYear = 2016;
        int endYear = localDate.getYear();
        int year = (int) (Math.random() * (endYear - startYear + 1)) + startYear;    //Random year
        int month = (int) (Math.random() * 12) + 1;
        Calendar c = Calendar.getInstance();                                //Create Calendar objects
        c.set(year, month, 0);                                        //Setting Date
        int numberOfDaysForMonth = c.get(Calendar.DAY_OF_MONTH);            //How many days to get the corresponding year and month
        int day = (int) (Math.random() * numberOfDaysForMonth + 1);

        com.iit.oop.backend.premierleague.model.Date nowDate = new com.iit.oop.backend.premierleague.model.Date(day, month, year);

        System.out.println(matches.size());
        Match generateMatch = new Match(name1, name2, score1, score2, points1, points2, nowDate);
        matches.add(generateMatch);
        System.out.println(matches.size());

        storeMatchDetails(matches);

        for (Match match : matches) {
            JSONObject obj = new JSONObject();//create json object
            //put values for json object like as key value pair
            obj.put("TeamOneName", match.getTeamOneName());
            obj.put("TeamTwoName", match.getTeamTwoName());
            obj.put("TeamOneGoalScore", match.getTeamOneScore());
            obj.put("TeamTwoGoalScore", match.getTeamTwoScore());
            obj.put("TeamOnePoints", match.getTeamOnePoint());
            obj.put("TeamTwoPoints", match.getTeamTwoPoint());
            obj.put("Date", match.getDateOfMatch().getDate());

            jsonArray.add(obj); //add json array
        }
        return jsonArray;


    }

    @CrossOrigin
    @RequestMapping("/searchByDate")
    public JSONArray searchByDate(@RequestParam(required = true) String date) throws IOException, ClassNotFoundException, ParseException { //http://localhost:8080/searchByDate?date=15/12/2020
        ArrayList<Match> matches = readAndLoadMatchData();
        JSONArray jsonArray = new JSONArray(); //json array

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Match> selectedMatch = new ArrayList<>();

        try {
            Date selectedDate = format.parse(date); //selected date convert to format
            for (Match m : matches) {
                Date matchDate = null;
                matchDate = format.parse(m.getDateOfMatch().getDate());
                if (selectedDate.compareTo(matchDate) == 0) { //match arrayList date compare with date picker selected date
                    selectedMatch.add(m); //add selected date match to new arrayList
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Match match : selectedMatch) {
            JSONObject obj = new JSONObject();//create json object
            //put values for json object like as key value pair
            obj.put("TeamOneName", match.getTeamOneName());
            obj.put("TeamTwoName", match.getTeamTwoName());
            obj.put("TeamOneGoalScore", match.getTeamOneScore());
            obj.put("TeamTwoGoalScore", match.getTeamTwoScore());
            obj.put("TeamOnePoints", match.getTeamOnePoint());
            obj.put("TeamTwoPoints", match.getTeamTwoPoint());
            obj.put("Date", match.getDateOfMatch().getDate());

            jsonArray.add(obj); //add json array

        }


        return jsonArray;
    }


}

