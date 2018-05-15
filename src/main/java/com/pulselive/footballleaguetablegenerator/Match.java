package com.pulselive.footballleaguetablegenerator;


public class Match {

	private String homeTeam;
	private String awayTeam;
	private int homeScore;
	private int awayScore;

	public Match() {
	}

	public Match(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	@Override
	public String toString() {
		return "Match{" +
			"homeTeam='" + homeTeam + '\'' +
			", awayTeam='" + awayTeam + '\'' +
			", homeScore=" + homeScore +
			", awayScore=" + awayScore +
			'}';
	}
}
