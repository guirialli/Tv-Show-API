package com.estudos.provider.series.local;

import java.text.ParseException;

import com.estudos.provider.series.imdb.ImdbService;
import com.estudos.provider.series.imdb.dto.DataSeason;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveImdbDataInDatabaseService extends Thread {

	private final String title;
	private final SeasonRepository seasonRepository;
	private final TvShowRepository tvShowRepository;
	private final EpisodeRepository episodeRepository;
	private final ImdbService imdbService;
	
	
	private void saveEpisode(DataSeason season, String seasonStr, Season seasonEtity) throws ParseException, JsonMappingException, JsonProcessingException {
		for (int e = 1; e <= season.episodes().size(); e++) {
			var episode = imdbService.findByEpisode(title, seasonStr, Integer.toString(e));
			var episodeEntity = new Episode(episode, seasonEtity);
			episodeRepository.save(episodeEntity);
		}
	}
	
	private void saveSeaonWithEpisodes(TvShow tvShowEntity) throws JsonMappingException, JsonProcessingException, ParseException {
		for (int s = 1; s <= tvShowEntity.getTotalSeasons(); s++) {
			String seasonStr = Integer.toString(s);
			var season = imdbService.findBySeason(title, seasonStr);
			var seasonEntity = new Season(season, tvShowEntity);
			seasonRepository.save(seasonEntity);
			
			saveEpisode(season, seasonStr, seasonEntity);
		}
	}

	@Override
	public void run() {
		try {
			var tvShow = imdbService.findTvShowByTitle(title);
			if (TvShow.isNA.test(tvShow.title()))
				throw new RuntimeException("Serie inexistente!");
			var tvShowEntity = new TvShow(tvShow);
			tvShowRepository.save(tvShowEntity);
			saveSeaonWithEpisodes(tvShowEntity);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Falha ao salvar a serie: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
