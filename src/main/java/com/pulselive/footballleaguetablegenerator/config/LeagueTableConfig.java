package com.pulselive.footballleaguetablegenerator.config;


import com.pulselive.footballleaguetablegenerator.Match;
import com.pulselive.footballleaguetablegenerator.service.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration for LeagueTable
 */
@Configuration
public class LeagueTableConfig {

	@Autowired
	IRepositoryService iRepositoryService;


	@Bean
	public List<Match> getMatches() {
		return iRepositoryService.findAllMatches();

	}


}
