package com.pulselive.footballleaguetablegenerator;

import com.pulselive.footballleaguetablegenerator.service.ILeagueTableComparatorService;
import com.pulselive.footballleaguetablegenerator.service.LeagueTableCalculatorService;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class LeagueTableCalculatorServiceTest {
	LeagueTableCalculatorService leagueTableCalculatorService;
	List<Match> matches;

	@Mocked
	ILeagueTableComparatorService leagueTableComparatorServiceMock = null;


	@Before
	public void setUp() {
		matches = createListOfMatches();
		leagueTableCalculatorService = new LeagueTableCalculatorService();
		leagueTableCalculatorService.setLeagueTableComparatorService(leagueTableComparatorServiceMock);


	}


	@After
	public void tearDown() {
		leagueTableCalculatorService = null;
	}

	private List<Match> createListOfMatches() {

		List<Match> matches = new ArrayList<>();
		;

		matches.add(new Match("Manchester United", "Arsenal", 3, 1));
		matches.add(new Match("Manchester City", "Liverpool", 1, 2));
		matches.add(new Match("Manchester City", "Manchester United", 2, 2));
		matches.add(new Match("Liverpool", "Arsenal", 3, 2));
		matches.add(new Match("Liverpool", "Arsenal", 3, 3));
		matches.add(new Match("Manchester United", "Liverpool", 0, 0));
		matches.add(new Match("Manchester United", "Manchester City", 1, 2));
		matches.add(new Match("Arsenal", "Manchester United", 2, 1));
		matches.add(new Match("Arsenal", "Manchester City", 1, 1));

		return matches;
	}

	private List<LeagueTableEntry> createLeagueTableEntries(String orderByField) {
		List<LeagueTableEntry> leagueTableEntries = new ArrayList<>();

		switch (orderByField) {

			case "points":
				leagueTableEntries.add(new LeagueTableEntry("Manchester United", 37,
					24, 6, 7, 107, 28, 79, 77));

				leagueTableEntries.add(new LeagueTableEntry("Arsenal", 37,
					18, 6, 13, 107, 51, 79, 79));

				leagueTableEntries.add(new LeagueTableEntry("Liverpool", 37,
					20, 12, 5, 107, 38, 79, 78));

				leagueTableEntries.add(new LeagueTableEntry("Manchester City", 37,
					31, 4, 2, 107, 27, 79, 80));

				break;

			case "goal difference":

				leagueTableEntries.add(new LeagueTableEntry("Manchester United", 37,
					24, 6, 7, 107, 28, 78, 70));

				leagueTableEntries.add(new LeagueTableEntry("Arsenal", 37,
					18, 6, 13, 107, 51, 67, 70));

				leagueTableEntries.add(new LeagueTableEntry("Liverpool", 37,
					20, 12, 5, 107, 38, 79, 70));

				leagueTableEntries.add(new LeagueTableEntry("Manchester City", 37,
					31, 4, 2, 107, 27, 76, 70));

				break;

			case "goals for":

				leagueTableEntries.add(new LeagueTableEntry("Manchester United", 37,
					24, 6, 7, 105, 28, 79, 70));

				leagueTableEntries.add(new LeagueTableEntry("Arsenal", 37,
					18, 6, 13, 107, 51, 79, 70));

				leagueTableEntries.add(new LeagueTableEntry("Liverpool", 37,
					20, 12, 5, 106, 38, 79, 70));

				leagueTableEntries.add(new LeagueTableEntry("Manchester City", 37,
					31, 4, 2, 108, 27, 79, 70));

				break;
			case "teamname":

				leagueTableEntries.add(new LeagueTableEntry("Manchester United", 37,
					24, 6, 7, 107, 28, 79, 70));

				leagueTableEntries.add(new LeagueTableEntry("Arsenal", 37,
					18, 6, 13, 107, 51, 79, 70));

				leagueTableEntries.add(new LeagueTableEntry("Liverpool", 37,
					20, 12, 5, 107, 38, 79, 70));

				leagueTableEntries.add(new LeagueTableEntry("Manchester City", 37,
					31, 4, 2, 107, 27, 79, 70));

				break;

			default:
				leagueTableEntries.add(new LeagueTableEntry("Manchester United", 37,
					24, 6, 7, 107, 28, 79, 78));

				leagueTableEntries.add(new LeagueTableEntry("Arsenal", 37,
					18, 6, 13, 107, 51, 79, 79));

				leagueTableEntries.add(new LeagueTableEntry("Liverpool", 37,
					20, 12, 5, 107, 38, 79, 78));

				leagueTableEntries.add(new LeagueTableEntry("Manchester City", 37,
					31, 4, 2, 107, 27, 79, 80));
				break;
		}

		return leagueTableEntries;
	}

	@Test
	public void testGetTeamNamesFromMatches() {
		Set<String> teamNames = leagueTableCalculatorService.getTeamNamesFromMatches(matches);
		assertNotNull(teamNames);
		assertEquals(4, teamNames.size());
		Set<String> expectedTeamNames = new TreeSet<>();
		expectedTeamNames.add("Arsenal");
		expectedTeamNames.add("Liverpool");
		expectedTeamNames.add("Manchester City");
		expectedTeamNames.add("Manchester United");
		assertEquals(expectedTeamNames, teamNames);
	}

	@Test
	public void testCalculateEntriesForListOfMatches() {
		List<LeagueTableEntry> leagueTableEntries = leagueTableCalculatorService.calculateEntriesForListOfMatches(matches);
		assertNotNull(leagueTableEntries);
		assertEquals(4, leagueTableEntries.size());
		LeagueTableEntry leagueTableEntryForArsenal = leagueTableEntries
			.stream()
			.filter(f -> f.getTeamName().equals("Arsenal"))
			.collect(Collectors.toList()).get(0);
		assertEquals("Arsenal", leagueTableEntryForArsenal.getTeamName());
		assertEquals(5, leagueTableEntryForArsenal.getPlayed());
		assertEquals(1, leagueTableEntryForArsenal.getWon());

		assertEquals(2, leagueTableEntryForArsenal.getDrawn());
		assertEquals(2, leagueTableEntryForArsenal.getLost());
		assertEquals(9, leagueTableEntryForArsenal.getGoalsFor());
		assertEquals(11, leagueTableEntryForArsenal.getGoalsAgainst());
		assertEquals(-2, leagueTableEntryForArsenal.getGoalDifference());
		assertEquals(5, leagueTableEntryForArsenal.getPoints());

	}

	@Test
	public void testSortLeagueTableByPoints() {

		new Expectations() {
			{
				leagueTableComparatorServiceMock.getLeagueTableEntryComparator();
				result = getComparator();
				times = 1;
			}
		};
		List<LeagueTableEntry> sortedLeagueTableEntries = leagueTableCalculatorService.sortLeagueTable(createLeagueTableEntries("points"));

		assertTrue(sortedLeagueTableEntries.get(0).getPoints() > sortedLeagueTableEntries.get(1).getPoints());
		assertTrue(sortedLeagueTableEntries.get(1).getPoints() > sortedLeagueTableEntries.get(2).getPoints());
		assertTrue(sortedLeagueTableEntries.get(2).getPoints() > sortedLeagueTableEntries.get(3).getPoints());

	}

	@Test
	public void testSortLeagueTableByGoalDifferenceAndPointsEqual() {
		new Expectations() {
			{
				leagueTableComparatorServiceMock.getLeagueTableEntryComparator();
				result = getComparator();
				times = 1;
			}
		};
		List<LeagueTableEntry> sortedLeagueTableEntries = leagueTableCalculatorService.sortLeagueTable(createLeagueTableEntries("goal difference"));

		assertTrue(sortedLeagueTableEntries.get(0).getGoalDifference() > sortedLeagueTableEntries.get(1).getGoalDifference());
		assertTrue(sortedLeagueTableEntries.get(1).getGoalDifference() > sortedLeagueTableEntries.get(2).getGoalDifference());
		assertTrue(sortedLeagueTableEntries.get(2).getGoalDifference() > sortedLeagueTableEntries.get(3).getGoalDifference());

	}

	@Test
	public void testSortLeagueTableByGoalsForAndPointsAndGoalsDiffEqual() {
		new Expectations() {
			{
				leagueTableComparatorServiceMock.getLeagueTableEntryComparator();
				result = getComparator();
				times = 1;
			}
		};
		List<LeagueTableEntry> sortedLeagueTableEntries = leagueTableCalculatorService.sortLeagueTable(createLeagueTableEntries("goals for"));

		assertTrue(sortedLeagueTableEntries.get(0).getGoalsFor() > sortedLeagueTableEntries.get(1).getGoalsFor());
		assertTrue(sortedLeagueTableEntries.get(1).getGoalsFor() > sortedLeagueTableEntries.get(2).getGoalsFor());
		assertTrue(sortedLeagueTableEntries.get(2).getGoalsFor() > sortedLeagueTableEntries.get(3).getGoalsFor());

	}

	@Test
	public void testSortLeagueTableByTeamNameAndOtherSortingFieldParametersEqual() {
		new Expectations() {
			{
				leagueTableComparatorServiceMock.getLeagueTableEntryComparator();
				result = getComparator();
				times = 1;
			}
		};
		List<LeagueTableEntry> sortedLeagueTableEntries = leagueTableCalculatorService.sortLeagueTable(createLeagueTableEntries("teamname"));

		assertTrue(sortedLeagueTableEntries.get(0).getTeamName().equals("Arsenal"));
		assertTrue(sortedLeagueTableEntries.get(1).getTeamName().equals("Liverpool"));
		assertTrue(sortedLeagueTableEntries.get(2).getTeamName().equals("Manchester City"));
		assertTrue(sortedLeagueTableEntries.get(3).getTeamName().equals("Manchester United"));
	}

	private Comparator<LeagueTableEntry> getComparator() {
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
