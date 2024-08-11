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
@RequestMapping("/tvshow/imdb")
public class ImdbController {

	@Autowired
	private ImdbRequestHandlerService requestHandlerService;

	@GetMapping
	public ResponseEntity<Object> getTvShow(@RequestParam() String title, @RequestParam(required = false) String season,
			@RequestParam(required = false) String episode) throws JsonMappingException, JsonProcessingException {
		return requestHandlerService.getTvShow(title, season, episode);
	}

	@GetMapping("/best")
	public ResponseEntity<Object> getTheBestTvShowEps(@RequestParam String title,
			@RequestParam(defaultValue = "10") String limit, @RequestParam(required = false) String season,
			@RequestParam(defaultValue = "false") String reverse) throws JsonMappingException, JsonProcessingException {
		return requestHandlerService.getTheBestTvShowEps(title, limit, season, reverse);

	}
}
