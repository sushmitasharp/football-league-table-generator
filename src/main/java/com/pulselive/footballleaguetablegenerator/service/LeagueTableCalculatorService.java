package com.pulselive.footballleaguetablegenerator.service;

import com.pulselive.footballleaguetablegenerator.LeagueTableEntry;
import com.pulselive.footballleaguetablegenerator.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LeagueTableCalculatorService implements ILeagueTableCalculatorService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ILeagueTableComparatorService leagueTableComparatorService;

	public void setLeagueTableComparatorService(ILeagueTableComparatorService leagueTableComparatorService) {
		this.leagueTableComparatorService = leagueTableComparatorService;
	}

	/**
	 * Get the set of all team-names who have played the matches.
	 *
	 * @param matches - the List of all completed matches
	 * @return Set of team-names
	 */
	@Override
	public Set<String> getTeamNamesFromMatches(List<Match> matches) {
		Set<String> teamNames = new TreeSet<>();
		matches
			.forEach(m -> {
				teamNames.add(m.getHomeTeam());
				teamNames.add(m.getAwayTeam());
			});

		return teamNames;
	}

	/**
	 * Calculate and populate leagueTable entries from the given list of matches
	 * Points are awarded based on Win:3 points,Drawn:1 points,Lost:0 points
	 * @param matches the List of all completed matches
	 * @return A list of leagueTableEntries
	 */
	@Override
	public List<LeagueTableEntry> calculateEntriesForListOfMatches(List<Match> matches) {
		List<LeagueTableEntry> leagueTableEntries = new ArrayList<>();
		if (matches == null || matches.isEmpty()) {
			return leagueTableEntries;
		}
		return populateLeagueTable(matches, getTeamNamesFromMatches(matches), leagueTableEntries);

	}

	/**
	 * Helps to populate the league table using matches and teamnames
	 *
	 * @param matches
	 * @param teamNames
	 * @param leagueTableEntries
	 * @return
	 */
	private List<LeagueTableEntry> populateLeagueTable(List<Match> matches,
	                                                   Set<String> teamNames,
	                                                   List<LeagueTableEntry> leagueTableEntries) {
		teamNames
			.forEach(t -> {
				List<Match> listOfMatchesPerTeam = matches
					.stream()
					.filter(f -> f.getAwayTeam().equals(t) || f.getHomeTeam().equals(t))
					.collect(Collectors.toList());
				leagueTableEntries.add(getLeagueTableEntryForEachTeam(t, listOfMatchesPerTeam));
			});


		return leagueTableEntries;
	}

	/**
	 * Populates leagueTable entry for each team
	 *
	 * @param team
	 * @param listOfMatchesPerTeam
	 * @return
	 */
	private LeagueTableEntry getLeagueTableEntryForEachTeam(String team, List<Match> listOfMatchesPerTeam) {
		LeagueTableEntry leagueTableEntry = new LeagueTableEntry(team, 0, 0, 0, 0, 0, 0, 0, 0);
		listOfMatchesPerTeam
			.forEach(m -> populateRestOfTheFieldsPerMatch(m, leagueTableEntry, team));

		logger.info("LeagueTableentry--->{}", leagueTableEntry);

		return leagueTableEntry;
	}

	/**
	 * Updates the entry in League Table per match
	 *
	 * @param m                - Match played by team
	 * @param leagueTableEntry
	 * @param team
	 */
	private void populateRestOfTheFieldsPerMatch(Match m, LeagueTableEntry leagueTableEntry, String team) {


		Boolean isHomeTeam = (m.getHomeTeam().equals(team)) ? true : false;
		Boolean isWonForGivenMatch = ((isHomeTeam && (m.getHomeScore() > m.getAwayScore())) ||
			(!isHomeTeam && (m.getAwayScore() > m.getHomeScore())));
		Boolean isDrawnForGivenMatch = (m.getHomeScore() == m.getAwayScore()) ? true : false;

		Integer calculateGoalsForGivenMatch = (isHomeTeam) ? m.getHomeScore() : m.getAwayScore();

		Integer calculateGoalsAgainstGivenMatch = (isHomeTeam) ? m.getAwayScore() : m.getHomeScore();

		Integer calculateGoalsDifferenceGivenMatch = calculateGoalsForGivenMatch - calculateGoalsAgainstGivenMatch;

		Integer calculatePointsGivenMatch = (isWonForGivenMatch) ? 3 : ((isDrawnForGivenMatch) ? 1 : 0);

		leagueTableEntry.setPlayed(leagueTableEntry.getPlayed() + 1);

		leagueTableEntry.setWon(leagueTableEntry.getWon() + ((isWonForGivenMatch) ? 1 : 0));
		leagueTableEntry.setDrawn(leagueTableEntry.getDrawn() + ((isDrawnForGivenMatch) ? 1 : 0));
		leagueTableEntry.setLost(leagueTableEntry.getLost() + ((isWonForGivenMatch || isDrawnForGivenMatch) ? 0 : 1));
		leagueTableEntry.setGoalsFor(leagueTableEntry.getGoalsFor() + calculateGoalsForGivenMatch);
		leagueTableEntry.setGoalsAgainst(leagueTableEntry.getGoalsAgainst() + calculateGoalsAgainstGivenMatch);
		leagueTableEntry.setGoalDifference(leagueTableEntry.getGoalDifference() + calculateGoalsDifferenceGivenMatch);
		leagueTableEntry.setPoints(leagueTableEntry.getPoints() + calculatePointsGivenMatch);

	}


	/**
	 * Sorts the leagueTableEntries based on the given leagueTableComparator
	 * where  leagueTableEntries objects are sorted by points, goalDifference, goalsFor and then teamName
	 *
	 * @param leagueTableEntries
	 * @return A list of sorted leagueTableEntries
	 */
	@Override
	public List<LeagueTableEntry> sortLeagueTable(List<LeagueTableEntry> leagueTableEntries) {
		logger.info("Sorting League Table Entries");

		Collections.sort(leagueTableEntries, leagueTableComparatorService.getLeagueTableEntryComparator());
		printLeagueTable(leagueTableEntries);
		return leagueTableEntries;
	}

	private void printLeagueTable(List<LeagueTableEntry> leagueTableEntries) {
		String str = String.format("%-25s%-10s%-10s%-10s%-10s%-10s%-15s%-15s%s","TEAMNAME",
			"PLAYED", "WON", "DRAWN", "LOST", "GOALSFOR", "GOALSAGAINST", "GOALDIFFERENCE", "POINTS");
		logger.info(str);
		leagueTableEntries.forEach(t->logger.info(String.format("%-25s%-10s%-10s%-10s%-10s%-10s%-16s%-15s%s"
			,t.getTeamName(),t.getPlayed(),t.getWon(),t.getDrawn(),t.getLost(),t.getGoalsFor(),
			t.getGoalsAgainst(),t.getGoalDifference(),t.getPoints())));
	}


}
