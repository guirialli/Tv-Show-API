package com.estudos.provider.series.imdb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeason(@JsonAlias("Title") String title, @JsonAlias("Season") String season,
		@JsonAlias("totalSeasons") String totalSeasons, @JsonAlias("Episodes") DataEpisodes[] episodes) {

}
