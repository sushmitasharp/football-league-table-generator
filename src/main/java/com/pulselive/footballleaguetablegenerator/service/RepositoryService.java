package com.pulselive.footballleaguetablegenerator.service;

import com.pulselive.footballleaguetablegenerator.Match;
import com.pulselive.footballleaguetablegenerator.repository.IMatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepositoryService implements IRepositoryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IMatchRepository matchRepository;

	/**
	 * Method for saving all matches to the repository
	 *
	 * @param matchList
	 */
	@Override
	public void saveMatches(List<Match> matchList) {
		matchRepository.saveMatches(matchList);
	}


	/**
	 * Method for retrieving all matches from the repository
	 *
	 * @return A list of matches found in the repository
	 */
	@Override
	public List<Match> findAllMatches() {
		List<Match> matches = matchRepository.findAllMatches();
		if (matches == null || matches.isEmpty()) {
			logger.info("No Entry found in the repository");
		}
		return matches;
	}

	/**
	 * Method for deleting all matches from the repository
	 */
	@Override
	public void deleteAllMatches() {
		matchRepository.clearList();

	}
}
