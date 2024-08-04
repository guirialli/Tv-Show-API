package com.estudos.provider.series.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.common.dto.ResponseError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/imdb")
public class ImdbController {

	@Autowired
	private ImdbService service;

	@GetMapping
	public ResponseEntity<Object> getTvShow(@RequestParam(required =  false) String title)
			throws JsonMappingException, JsonProcessingException {
		if (title != null) {
			var tvShow = this.service.findTvShowByTitle(title);
			return ResponseEntity.ok(tvShow);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseError(400, "You need insert a param like: title!"));
	}
}
