package com.pulselive.footballleaguetablegenerator.service;

import com.pulselive.footballleaguetablegenerator.LeagueTableEntry;

import java.util.Comparator;

public interface ILeagueTableComparatorService {

	/**
	 * Returns a comparator for sorting leagueTableEntries
	 *
	 * @return Comparator based on the sort criteria
	 */
	Comparator<LeagueTableEntry> getLeagueTableEntryComparator();
}
