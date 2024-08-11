package com.estudos.provider.series.local;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estudos.provider.series.imdb.ImdbRequestHandlerService;
import com.estudos.provider.series.local.entity.TvShowRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class LocalRequestHandlerService {
	@Autowired
	private TvShowRepository tvShowRepository;
	@Autowired
	private ImdbRequestHandlerService imdbRequestHandlerService;
	@Autowired
	private LocalService localService;
	@Autowired
	SaveImdbDataInDatabaseService dataInDatabaseService;

	public ResponseEntity<Object> getTvShow(String title, Integer season, Integer episode)
			throws JsonMappingException, JsonProcessingException, BadRequestException {
		var tvShow = tvShowRepository.findByTitle(title);
		if (tvShow.isEmpty()) {
			dataInDatabaseService.save(title);
			String seasonStr = season == null? null : season.toString();
			String episodeStr = episode == null? null: episode.toString();
			
			return imdbRequestHandlerService.getTvShow(title, seasonStr, episodeStr);
		}
		return ResponseEntity.ok(localService.findTvShow(title, season, episode));

	}
}
