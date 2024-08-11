package com.estudos.provider.series.local;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/tvshow")
public class LocalController {

	@Autowired
	LocalRequestHandlerService handlerService;

	@GetMapping
	public ResponseEntity<Object> getTvShow(@RequestParam() String title,
			@RequestParam(required = false) Integer season, @RequestParam(required = false) Integer episode)
			throws JsonMappingException, JsonProcessingException, BadRequestException {
		return handlerService.getTvShow(title, season, episode);
	}

	@GetMapping("/best")
	public ResponseEntity<Object> filterBestEpisodes(@RequestParam() String title,
			@RequestParam(defaultValue = "10", required = false) int limit,
			@RequestParam(required = false) Integer season) {
		return this.handlerService.filterBestEpisodes(title, season, limit);

	}
}
