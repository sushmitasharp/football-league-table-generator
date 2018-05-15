package com.pulselive.footballleaguetablegenerator.controller;


import com.pulselive.footballleaguetablegenerator.LeagueTable;
import com.pulselive.footballleaguetablegenerator.LeagueTableEntry;
import com.pulselive.footballleaguetablegenerator.Match;
import com.pulselive.footballleaguetablegenerator.service.IRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for LeagueTable
 */
@RestController
public class LeagueTableController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LeagueTable leagueTable;

	@Autowired
	IRepositoryService iRepositoryService;

	/**
	 * GET method for retrieving the leagueTable entries
	 *
	 * @return A list of league table entries
	 */
	@GetMapping("/league-table")
	public List<LeagueTableEntry> getLeagueTableEntries() {
		logger.info("Retrieving League-Table");
		return leagueTable.getTableEntries();
	}

	/**
	 * POST method for adding matches for creation or updation of leagueTableEntries
	 *
	 * @param matches List of matches that needs to be added
	 */
	@PostMapping("/league/matches")
	public void addMatches(@RequestBody List<Match> matches) {
		logger.info("Adding Matches");
		iRepositoryService.saveMatches(matches);
	}

	/**
	 * GET method for retrieving the list of matches already stored in the repository
	 *
	 * @return List of matches that are present currently in the repository
	 */
	@GetMapping("/league/matches")
	public List<Match> getLeagueMatches() {
		logger.info("Retrieving Matches");
		return iRepositoryService.findAllMatches();
	}

	/**
	 * DELETE method for removing all the matches stored in the repository
	 */
	@DeleteMapping("/league/matches")
	public void deleteAllMatches() {
		logger.info("Deleting Matches");
		iRepositoryService.deleteAllMatches();

	}
}
