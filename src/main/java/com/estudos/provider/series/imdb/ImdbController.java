package com.estudos.provider.series.imdb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/imdb")
public class ImdbController {

	@Autowired
	private ImdbService service;

	@GetMapping
	public ResponseEntity<Object> getTvShow(@RequestParam() String title, @RequestParam(required = false) String season,
			@RequestParam(required = false) String episode) throws JsonMappingException, JsonProcessingException {
		Object response;
		if (season != null && episode != null)
			response = this.service.findByEpisode(title, season, episode);
		else if (season != null)
			response = this.service.findBySeason(title, season);
		else
			response = this.service.findTvShowByTitle(title);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/best")
	public ResponseEntity<Object> getTheBestTvShowEps(@RequestParam String title,
			@RequestParam(defaultValue = "10") String limit, @RequestParam(required = false) String season,
			@RequestParam(defaultValue = "false") String reverse) throws JsonMappingException, JsonProcessingException {

		var limitInt = Integer.parseInt(limit);
		var desc = reverse.toLowerCase().equals("true");
		Object eps;
		
		if (season == null)
			eps = this.service.filterByBestEpisodes(title, limitInt, desc);
		else
			eps = this.service.filterByBestEpisodes(title, limitInt, season, desc);
		
		return ResponseEntity.ok(eps);
	}
}
