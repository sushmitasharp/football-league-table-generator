package com.pulselive.footballleaguetablegenerator;

import com.pulselive.footballleaguetablegenerator.service.ILeagueTableCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeagueTable {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	List<Match> matches;

	@Autowired
	ILeagueTableCalculatorService leagueTableCalculatorService;


	public LeagueTable(final List<Match> matches) {
		this.matches = matches;

	}

	/**
	 * Get a list of Matches
	 *
	 * @return
	 */
	public List<Match> getMatches() {
		return matches;
	}

	/**
	 * Get the ordered list of league table entries for this league table.
	 *
	 * @return A sorted list of league table entries
	 */
	public List<LeagueTableEntry> getTableEntries() {
		logger.info("Calculating LeagueTable Entries ....");
		List<LeagueTableEntry> leagueTableEntries = leagueTableCalculatorService.calculateEntriesForListOfMatches(this.matches);
		if (leagueTableEntries.isEmpty()) {
			return leagueTableEntries;
		}
		return leagueTableCalculatorService.sortLeagueTable(leagueTableEntries);
	}

	public void setLeagueTableCalculatorService(ILeagueTableCalculatorService leagueTableCalculatorService) {
		this.leagueTableCalculatorService = leagueTableCalculatorService;
	}
}
