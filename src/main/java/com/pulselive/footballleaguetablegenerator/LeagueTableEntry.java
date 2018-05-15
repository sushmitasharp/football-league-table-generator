package com.pulselive.footballleaguetablegenerator;

public class LeagueTableEntry {

	private String teamName;
	private int played;
	private int won;
	private int drawn;
	private int lost;
	private int goalsFor;
	private int goalsAgainst;
	private int goalDifference;
	private int points;


	public LeagueTableEntry(String teamName, int played, int won, int drawn,
	                        int lost, int goalsFor, int goalsAgainst, int goalDifference, int points) {
		this.teamName = teamName;
		this.played = played;
		this.won = won;
		this.drawn = drawn;
		this.lost = lost;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
		this.goalDifference = goalDifference;
		this.points = points;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getDrawn() {
		return drawn;
	}

	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "LeagueTableEntry{" +
			"teamName='" + teamName + '\'' +
			", played=" + played +
			", won=" + won +
			", drawn=" + drawn +
			", lost=" + lost +
			", goalsFor=" + goalsFor +
			", goalsAgainst=" + goalsAgainst +
			", goalDifference=" + goalDifference +
			", points=" + points +
			'}';
	}
}
