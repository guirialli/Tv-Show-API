package com.estudos.provider.series.imdb;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	private ImdbSort sort;

	@Value("${application.imdb.keys}")
	private String apiKey;
	@Value("${application.imdb.url}")
	private String apiUrl;

	private String defaultUrlTitle(String title) {
		return apiUrl + "?t=" + convertParam.parser(title) + "&apikey=" + apiKey;
	}

	// Busca na API por um show
	public DataTvShow findTvShowByTitle(String title) throws JsonMappingException, JsonProcessingException {
		String url = this.defaultUrlTitle(title);
		var json = this.consumer.getData(url);
		return this.convertData.parser(json, DataTvShow.class);
	}

	// Busca na API por uma temporada
	public DataSeason findBySeason(String title, String season) throws JsonMappingException, JsonProcessingException {
		String url = this.defaultUrlTitle(title) + "&season=" + this.convertParam.parser(season);
		var json = this.consumer.getData(url);
		return this.convertData.parser(json, DataSeason.class);
	}

	// Busca na API por um ep
	public DataEpisodes findByEpisode(String title, String season, String episode)
			throws JsonMappingException, JsonProcessingException {
		String url = this.defaultUrlTitle(title) + "&season=" + this.convertParam.parser(season) + "&episode="
				+ this.convertParam.parser(episode);

		var json = this.consumer.getData(url);
		return this.convertData.parser(json, DataEpisodes.class);
	}

	// Busca pelos eps e filtra por temporada
	public List<DataEpisodes> filterBySeason(String title, String season)
			throws JsonMappingException, JsonProcessingException {
		List<DataEpisodes> eps = new ArrayList<>();

		var tvShow = this.findTvShowByTitle(title);
		var seasonInt = Integer.parseInt(season);
		var tvShowSeasonInt = Integer.parseInt(tvShow.seasons());
		
		if (tvShow.titulo() != null && tvShow.seasons() != null && seasonInt <= tvShowSeasonInt) {
			var seasonData = this.findBySeason(title, season);
			for (var ep : seasonData.episodes()) {
				eps.add(new DataEpisodes(ep.title(), season, ep.episode(), ep.description(), ep.year(), ep.released(),
						ep.runtime(), ep.rating()));
			}
		}
		return eps;
	}

	// Busca por os eps de um show e depois devolve ordenado por availiação
	public List<DataEpisodes> filterByBestEpisodes(String title, String season, boolean reverse)
			throws JsonMappingException, JsonProcessingException {
		var eps = this.filterBySeason(title, season);
		eps = eps.stream().sorted(this.sort.EPISODE_COMPARATOR).toList();
		return !reverse ? eps.reversed() : eps;
	}

	// Busca por os eps de um show e depois devolve ordenado por availiação
	public List<DataEpisodes> filterByBestEpisodes(String title, boolean reverse)
			throws JsonMappingException, JsonProcessingException {

		List<DataEpisodes> eps = new ArrayList<>();
		var tvShow = this.findTvShowByTitle(title);

		if (tvShow.titulo() != null && tvShow.seasons() != null) {
			var seasons = Integer.parseInt(tvShow.seasons());
			for (int i = 1; i <= seasons; i++) {
				String season = Integer.toString(i);
				eps.addAll(this.filterBySeason(title, season));
			}
			eps = eps.stream().sorted(this.sort.EPISODE_COMPARATOR).toList();
		}
		return !reverse ? eps.reversed() : eps;
	}

	// Busca pleos melhores eps e limita
	public List<DataEpisodes> filterByBestEpisodes(String title, int limit, boolean reverse)
			throws JsonMappingException, JsonProcessingException {
		return limit <= 0 ? new ArrayList<>() : subList(this.filterByBestEpisodes(title, reverse), limit);
	}

	// Busca pelos melhores filtra pela season e limita
	public List<DataEpisodes> filterByBestEpisodes(String title, int limit, String season, boolean reverse)
			throws JsonMappingException, JsonProcessingException {
		return limit <= 0 ? new ArrayList<>() : subList(this.filterByBestEpisodes(title, season, reverse), limit);
	}

	private List<DataEpisodes> subList(List<DataEpisodes> list, int limit) {
		if (list.size() == 0)
			return new ArrayList<>();
		return list.subList(0, limit > list.size() && limit < 0 ? list.size() : limit);
	}
}
