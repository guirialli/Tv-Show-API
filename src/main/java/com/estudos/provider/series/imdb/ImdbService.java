package com.estudos.provider.series.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.estudos.common.convert.ConvertData;
import com.estudos.common.http.ApiConsumer;
import com.estudos.provider.series.imdb.dto.DataTvShow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ImdbService {

	@Autowired
	private ApiConsumer consumer;
	@Autowired
	private ConvertData convertData;

	@Value("${application.imdb.keys}")
	private String apiKey;
	@Value("${application.imdb.url}")
	private String apiUrl;

	public DataTvShow findTvShowByTitle(String title) throws JsonMappingException, JsonProcessingException {
		String url = apiUrl + "?t=" + title + "&apikey=" + apiKey;
		var jsonResponse = this.consumer.getData(url);
		return this.convertData.parser(jsonResponse, DataTvShow.class);
	}
}
