package com.pulselive.footballleaguetablegenerator.service;

import com.pulselive.footballleaguetablegenerator.LeagueTableEntry;
import com.pulselive.footballleaguetablegenerator.Match;

import java.util.List;
import java.util.Set;

public interface ILeagueTableCalculatorService {

	/**
	 * Get the set of all team-names who have played the matches.
	 *
	 * @param matches - the List of all completed matches
	 * @return Set of team-names
	 */
	Set<String> getTeamNamesFromMatches(List<Match> matches);

	/**
	 * Calculate and populate leagueTable entries from the given list of matches
	 *
	 * @param matches - the List of all completed matches
	 * @return A list of leagueTableEntries
	 */
	List<LeagueTableEntry> calculateEntriesForListOfMatches(List<Match> matches);


	/**
	 * Sorts the leagueTableEntries based on the given leagueTableComparator
	 *
	 * @param leagueTableEntries
	 * @return A list of sorted leagueTableEntries
	 */
	List<LeagueTableEntry> sortLeagueTable(List<LeagueTableEntry> leagueTableEntries);
}
