package com.estudos.provider.series.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ImdbRequestHandlerService {

	@Autowired
	private ImdbService service;

	public ResponseEntity<Object> getTvShow(String title, String season, String episode)
			throws JsonMappingException, JsonProcessingException {
		Object response;
		if (season != null && episode != null)
			response = this.service.findByEpisode(title, season, episode);
		else if (season != null)
			response = this.service.findBySeason(title, season);
		else
			response = this.service.findTvShowByTitle(title);
		return ResponseEntity.ok(response);

	}

	public ResponseEntity<Object> getTheBestTvShowEps(String title, String limit, String season, String reverse)
			throws JsonMappingException, JsonProcessingException {
		var limitInt = Integer.parseInt(limit);
		var desc = reverse.toLowerCase().equals("true");
		Object eps;

		if (limitInt > 100)
			limitInt = 50;
		else if (limitInt < 0)
			limitInt = 10;

		if (season == null)
			eps = this.service.filterByBestEpisodes(title, limitInt, desc);
		else
			eps = this.service.filterByBestEpisodes(title, limitInt, season, desc);

		return ResponseEntity.ok(eps);
	}
}
