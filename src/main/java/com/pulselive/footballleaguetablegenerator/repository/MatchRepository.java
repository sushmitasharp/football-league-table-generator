package com.pulselive.footballleaguetablegenerator.repository;

import com.pulselive.footballleaguetablegenerator.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements {@code IMatchRepository} for
 */
@Repository
public class MatchRepository implements IMatchRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	List<Match> matches = new ArrayList<>();

	/**
	 * Returns the list of Matches present in the repository
	 *
	 * @return all the matches that are currently present in the repository
	 */
	@Override
	public List<Match> findAllMatches() {
		logger.info("Finding All Completed Matches");
		return matches;
	}

	/**
	 * Adds a list Of Matches to the repository
	 *
	 * @param matchesList - The list of Matches that needs to be added to the repository
	 */
	@Override
	public void saveMatches(List<Match> matchesList) {
		logger.info("Saving All Completed Matches");
		matchesList.forEach(m -> matches.add(m));
	}

	/**
	 * Deletes all the Matches from repository
	 */
	@Override
	public void clearList() {
		logger.info("Deleting All Saved Matches");
		matches.clear();
	}

}
