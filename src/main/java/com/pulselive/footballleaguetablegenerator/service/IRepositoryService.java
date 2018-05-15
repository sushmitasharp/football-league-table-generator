package com.pulselive.footballleaguetablegenerator.service;

import com.pulselive.footballleaguetablegenerator.Match;

import java.util.List;

public interface IRepositoryService {
	/**
	 * Method for saving all matches to the repository
	 *
	 * @param matchList
	 */
	void saveMatches(List<Match> matchList);


	/**
	 * Method for retrieving all matches from the repository
	 *
	 * @return A list of matches found in the repository
	 */
	List<Match> findAllMatches();

	/**
	 * Method for deleting all matches from the repository
	 */
	void deleteAllMatches();
}
