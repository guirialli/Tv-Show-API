package com.estudos.provider.series.imdb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisodes(@JsonAlias("Title") String title, @JsonAlias("Season") String season,
		@JsonAlias("Episode") String episode, @JsonAlias("Plot") String description, @JsonAlias("Year") String year,
		@JsonAlias("Released") String released, @JsonAlias("Runtime") String runtime,
		@JsonAlias("imdbRating") String rating) {

}
