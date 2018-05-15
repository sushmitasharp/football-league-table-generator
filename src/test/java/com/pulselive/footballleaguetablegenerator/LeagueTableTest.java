package com.pulselive.footballleaguetablegenerator;


import com.pulselive.footballleaguetablegenerator.service.ILeagueTableCalculatorService;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(JMockit.class)
public class LeagueTableTest {
	LeagueTable leagueTable;

	@Mocked
	ILeagueTableCalculatorService leagueTableCalculatorService;

	List<Match> matches;

	List<LeagueTableEntry> leagueTableEntries;
	List<LeagueTableEntry> sortedLeagueTableEntries;

	@Before
	public void setUp() {

		matches = createListOfMatches("Basic");
		leagueTable = new LeagueTable(matches);
		leagueTable.setLeagueTableCalculatorService(leagueTableCalculatorService);

		 leagueTableEntries = getLeagueTableEntries();
		sortedLeagueTableEntries = getSortedLeagueTableEntries();


	}

	@After
	public void tearDown() {
		leagueTable = null;

	}

	private List<Match> createListOfMatches(String matchingType) {

		List<Match> matches = new ArrayList<>();

		switch (matchingType) {
			case "Basic":
				matches.add(new Match("Manchester United", "Arsenal", 3, 1));
				matches.add(new Match("Manchester City", "Liverpool", 1, 2));
				matches.add(new Match("Manchester City", "Manchester United", 2, 2));
				matches.add(new Match("Liverpool", "Arsenal", 3, 2));
				matches.add(new Match("Liverpool", "Arsenal", 3, 3));
				matches.add(new Match("Manchester United", "Liverpool", 0, 0));
				matches.add(new Match("Manchester United", "Manchester City", 1, 2));
				matches.add(new Match("Arsenal", "Manchester United", 2, 1));
				matches.add(new Match("Arsenal", "Manchester City", 1, 1));
				break;

			case "OrderByPoints":

				matches.add(new Match("Manchester United", "Arsenal", 3, 1));
				matches.add(new Match("Manchester City", "Liverpool", 1, 2));
				matches.add(new Match("Manchester City", "Manchester United", 2, 2));
				break;
			default:
				return null;
		}
		return matches;
	}


	@Test
	public void testToCheckSortingOrder() {

		new Expectations() {
			{
				leagueTableCalculatorService.calculateEntriesForListOfMatches(matches);
				times = 1;
				result = leagueTableEntries;

				leagueTableCalculatorService.sortLeagueTable(leagueTableEntries);
				times = 1;
				result = sortedLeagueTableEntries;

			}
		};

		List<LeagueTableEntry> leagueTableEntries = leagueTable.getTableEntries();
		assertEquals("Liverpool", leagueTableEntries.get(0).getTeamName());
		assertEquals("Manchester United", leagueTableEntries.get(1).getTeamName());
		assertEquals("Manchester City", leagueTableEntries.get(2).getTeamName());
		assertEquals("Arsenal", leagueTableEntries.get(3).getTeamName());
	}

	private List<LeagueTableEntry> getSortedLeagueTableEntries() {
		List<LeagueTableEntry> sortedLeagueTableEntries = new ArrayList<>();


		sortedLeagueTableEntries.add(new LeagueTableEntry("Liverpool", 4,
			2, 2, 0, 8, 6, 2, 8));

		sortedLeagueTableEntries.add(new LeagueTableEntry("Manchester United", 5,
			1, 2, 2, 7, 7, 0, 5));

		sortedLeagueTableEntries.add(new LeagueTableEntry("Manchester City", 4,
			1, 2, 1, 6, 6, 0, 5));
		sortedLeagueTableEntries.add(new LeagueTableEntry("Arsenal", 5,
			1, 2, 2, 9, 11, 2, 5));
		return sortedLeagueTableEntries;
	}

	private List<LeagueTableEntry> getLeagueTableEntries() {
		List<LeagueTableEntry> leagueTableEntries = new ArrayList<>();

		leagueTableEntries.add(new LeagueTableEntry("Arsenal", 5,
			1, 2, 2, 9, 11, 2, 5));

		leagueTableEntries.add(new LeagueTableEntry("Liverpool", 4,
			2, 2, 0, 8, 6, 2, 8));

		leagueTableEntries.add(new LeagueTableEntry("Manchester City", 4,
			1, 2, 1, 6, 6, 0, 5));

		leagueTableEntries.add(new LeagueTableEntry("Manchester United", 5,
			1, 2, 2, 7, 7, 0, 5));
		return leagueTableEntries;
	}


}
