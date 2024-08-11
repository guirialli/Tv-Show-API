package com.estudos.provider.series.local;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.estudos.provider.series.imdb.ImdbService;
import com.estudos.provider.series.imdb.dto.DataSeason;
import com.estudos.provider.series.local.entity.Episode;
import com.estudos.provider.series.local.entity.EpisodeRepository;
import com.estudos.provider.series.local.entity.Season;
import com.estudos.provider.series.local.entity.SeasonRepository;
import com.estudos.provider.series.local.entity.TvShow;
import com.estudos.provider.series.local.entity.TvShowRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class SaveImdbDataInDatabaseService {

	@Autowired
	private SeasonRepository seasonRepository;
	@Autowired
	private TvShowRepository tvShowRepository;
	@Autowired
	private EpisodeRepository episodeRepository;
	@Autowired
	private ImdbService imdbService;

	private void saveEpisode(String title, DataSeason season, String seasonStr, Season seasonEtity)
			throws ParseException, JsonMappingException, JsonProcessingException {
		for (int e = 1; e <= season.episodes().size(); e++) {
			var episode = imdbService.findByEpisode(title, seasonStr, Integer.toString(e));
			var episodeEntity = new Episode(episode, seasonEtity);
			if (!episodeEntity.getTitle().equals( "N/A"))
				episodeRepository.save(episodeEntity);
		}
	}

	private void saveSeaonWithEpisodes(String title, TvShow tvShowEntity)
			throws JsonMappingException, JsonProcessingException, ParseException {
		for (int s = 1; s <= tvShowEntity.getTotalSeasons(); s++) {
			String seasonStr = Integer.toString(s);
			var season = imdbService.findBySeason(title, seasonStr);
			var seasonEntity = new Season(season, tvShowEntity);
			seasonEntity.setSeason(s);
			seasonRepository.save(seasonEntity);

			saveEpisode(title, season, seasonStr, seasonEntity);
		}
	}

	@Async
	public void save(String title) {
		try {
			var tvShow = imdbService.findTvShowByTitle(title);
			if (TvShow.isNA.test(tvShow.title()))
				throw new RuntimeException("Serie inexistente!");
			var tvShowEntity = new TvShow(tvShow);
			tvShowRepository.save(tvShowEntity);
			saveSeaonWithEpisodes(title, tvShowEntity);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Falha ao salvar a serie: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
