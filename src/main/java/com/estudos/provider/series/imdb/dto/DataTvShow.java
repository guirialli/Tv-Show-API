package com.estudos.provider.series.imdb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTvShow(@JsonAlias("Title") String titulo, @JsonAlias("Plot") String description,
		@JsonAlias("totalSeasons") String seasons, @JsonAlias("Released") String released,
		@JsonAlias("Genre") String genre, @JsonAlias("Actors") String actors, @JsonAlias("Writer") String writer,
		@JsonAlias("Metascore") String metascore, @JsonAlias("imdbRating") String rating) {

}
