package com.estudos.provider.series.local;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.common.http.exceptions.NotFoundException;
import com.estudos.provider.series.local.entity.Episode;
import com.estudos.provider.series.local.entity.EpisodeRepository;
import com.estudos.provider.series.local.entity.Season;
import com.estudos.provider.series.local.entity.SeasonRepository;
import com.estudos.provider.series.local.entity.TvShow;
import com.estudos.provider.series.local.entity.TvShowRepository;

@Service
public class LocalService {
	@Autowired
	private TvShowRepository tvShowRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private EpisodeRepository episodeRepository;
	
	public Object findTvShow(String title, Integer season, Integer episode) throws BadRequestException {
		if(title == null)
			throw new BadRequestException("Title cannot be null!");
		else if (season != null && episode != null)
			return this.findTvShowEpisode(title, season, episode);
		else if(season != null )
			return this.findTvShowSeason(title, season);
		return this.findTvShow(title);
	}

 	public TvShow findTvShow(String title) {
		return this.tvShowRepository.findByTitle(title)
				.orElseThrow(() -> new NotFoundException("Not found Tv Show with title: " + title));

	}

	public Season findTvShowSeason(String title, int season) {
		return seasonRepository.findByTitleAndSeason(title, season).orElseThrow(
				() -> new NotFoundException("Not found season with tv show title " + title + "or season " + season));
	}

	public Episode findTvShowEpisode(String title, int season, int episode) {
		var seasonEntity = this.findTvShowSeason(title, season);
		String seasonId = seasonEntity.getId();
		return episodeRepository.findByEpisodeAndTitleAndSeason(episode, title, seasonId)
				.orElseThrow(() -> new NotFoundException("Not found Episode with tv show  title " + title + " season "
						+ season + "or episode " + episode));
	}

}
