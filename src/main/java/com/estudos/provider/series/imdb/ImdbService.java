package com.estudos.provider.series.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.estudos.common.convert.ConvertData;
import com.estudos.common.convert.ConvertParam;
import com.estudos.common.http.ApiConsumer;
import com.estudos.provider.series.imdb.dto.DataEpisodes;
import com.estudos.provider.series.imdb.dto.DataSeason;
import com.estudos.provider.series.imdb.dto.DataTvShow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ImdbService {

	@Autowired
	private ApiConsumer consumer;
	@Autowired
	private ConvertData convertData;
	@Autowired
	private ConvertParam convertParam;

	@Value("${application.imdb.keys}")
	private String apiKey;
	@Value("${application.imdb.url}")
	private String apiUrl;

	private String defaultUrlTitle(String title) {
		return apiUrl + "?t=" + convertParam.parser(title) + "&apikey=" + apiKey;
	}

	public DataTvShow findTvShowByTitle(String title) throws JsonMappingException, JsonProcessingException {
		String url = this.defaultUrlTitle(title);
		var json = this.consumer.getData(url);
		return this.convertData.parser(json, DataTvShow.class);
	}

	public DataSeason findBySeason(String title, String season)
			throws JsonMappingException, JsonProcessingException {
		String url = this.defaultUrlTitle(title) + "&season=" + this.convertParam.parser(season);
		var json = this.consumer.getData(url);
		return this.convertData.parser(json, DataSeason.class);
	}

	public DataEpisodes findByEpisode(String title, String season, String episode)
			throws JsonMappingException, JsonProcessingException {
		String url = this.defaultUrlTitle(title) + "&season=" + this.convertParam.parser(season) + "&episode="
				+ this.convertParam.parser(episode);

		var json = this.consumer.getData(url);
		return this.convertData.parser(json, DataEpisodes.class);
	}
}
