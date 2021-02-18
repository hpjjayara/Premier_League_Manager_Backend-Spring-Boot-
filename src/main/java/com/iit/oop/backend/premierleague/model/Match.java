package com.iit.oop.backend.premierleague.model;

import java.io.Serializable;

public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    private String teamOneName;
    private String teamTwoName;
    private int teamOneScore;
    private int teamTwoScore;
    private int teamOnePoint;
    private int teamTwoPoint;
    private Date dateOfMatch;

    public Match(String teamOneName, String teamTwoName, int teamOneScore, int teamTwoScore, int teamOnePoint, int teamTwoPoint, Date dateOfMatch) {
        this.teamOneName = teamOneName;
        this.teamTwoName = teamTwoName;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
        this.teamOnePoint = teamOnePoint;
        this.teamTwoPoint = teamTwoPoint;
        this.dateOfMatch = dateOfMatch;
    }

    public Match(String name1, String name2, int score1, int score2, int points1, int points2, java.util.Date nowDate) {

    }

    public String getTeamOneName() {
        return teamOneName;
    }

    public void setTeamOneName(String teamOneName) {
        this.teamOneName = teamOneName;
    }

    public String getTeamTwoName() {
        return teamTwoName;
    }

    public void setTeamTwoName(String teamTwoName) {
        this.teamTwoName = teamTwoName;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(int teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(int teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public int getTeamOnePoint() {
        return teamOnePoint;
    }

    public void setTeamOnePoint(int teamOnePoint) {
        this.teamOnePoint = teamOnePoint;
    }

    public int getTeamTwoPoint() {
        return teamTwoPoint;
    }

    public void setTeamTwoPoint(int teamTwoPoint) {
        this.teamTwoPoint = teamTwoPoint;
    }

    public Date getDateOfMatch() {
        return dateOfMatch;
    }


    public void setDateOfMatch(Date dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }


    @Override
    public String toString() {
        return "Matches    -------->  " +
                "\nTeam one name    : " + teamOneName +
                "\nTeam one score   : " + teamOneScore +
                "\nTeam one point   : " + teamOnePoint +
                "\nTeam two name    : " + teamTwoName +
                "\nTeam two score   : " + teamTwoScore +
                "\nTeam two point   : " + teamTwoPoint +
                "\nDate of Match    : " + dateOfMatch.getDate() + "\n";
    }

}
