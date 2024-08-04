package com.estudos.provider.series.imdb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTvShow(@JsonAlias("Title") String titulo, @JsonAlias("totalSeasons") String seasons,
		@JsonAlias("Released") String released, @JsonAlias("Genre") String genre,
		@JsonAlias("Metascore") String metascore, @JsonAlias("imdbRating") String rating) {

}
