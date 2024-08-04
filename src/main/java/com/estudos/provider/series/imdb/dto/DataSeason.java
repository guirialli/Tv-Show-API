package com.estudos.provider.series.imdb.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeason(@JsonAlias("Title") String title, @JsonAlias("Season") String season,
		@JsonAlias("totalSeasons") String totalSeasons, @JsonAlias("Episodes") List<DataEpisodes> episodes) {

}
