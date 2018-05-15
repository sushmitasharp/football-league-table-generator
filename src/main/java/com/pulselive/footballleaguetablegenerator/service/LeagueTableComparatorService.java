package com.pulselive.footballleaguetablegenerator.service;

import com.pulselive.footballleaguetablegenerator.LeagueTableEntry;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class LeagueTableComparatorService implements ILeagueTableComparatorService {

	/**
	 * Returns a comparator for sorting leagueTableEntries
	 *
	 * @return Comparator based on the sort criteria
	 */
	@Override
	public Comparator<LeagueTableEntry> getLeagueTableEntryComparator() {
		Comparator<LeagueTableEntry> leagueTableComparatorPoints = Comparator.comparingInt(LeagueTableEntry::getPoints);
		Comparator<LeagueTableEntry> leagueTableComparatorGoalDifference = Comparator.comparingInt(LeagueTableEntry::getGoalDifference);
		Comparator<LeagueTableEntry> leagueTableComparatorGoalsFor = Comparator.comparingInt(LeagueTableEntry::getGoalsFor);
		Comparator<LeagueTableEntry> leagueTableComparatorTeamName = Comparator.comparing(LeagueTableEntry::getTeamName);

		return leagueTableComparatorPoints.reversed()
			.thenComparing(leagueTableComparatorGoalDifference.reversed())
			.thenComparing(leagueTableComparatorGoalsFor.reversed())
			.thenComparing(leagueTableComparatorTeamName);
	}
}
