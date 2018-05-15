package com.pulselive.footballleaguetablegenerator.repository;

import com.pulselive.footballleaguetablegenerator.Match;

import java.util.List;

public interface IMatchRepository {

	/**
	 * Returns the list of Matches present in the repository
	 *
	 * @return all the matches that are currently present in the repository
	 */
	List<Match> findAllMatches();

	/**
	 * Adds a list Of Matches to the repository
	 *
	 * @param matchesList - The list of Matches that needs to be added to the repository
	 */
	void saveMatches(List<Match> matchesList);

	/**
	 * Deletes all the Matches from repository
	 */
	void clearList();
}
