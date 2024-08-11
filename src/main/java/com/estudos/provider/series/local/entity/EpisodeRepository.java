package com.estudos.provider.series.local.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, String> {
	public List<Episode> findAllByTitle(String title);
	
	public List<Episode> findByEpisodeAndTitle(int episode, String title);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title LIKE %:title% AND s.id = :seasonId AND e.episode = :episode")
	public Optional<Episode> findByEpisodeAndTitleAndSeason(@Param("episode") int episode, @Param("title") String title, @Param("seasonId") String seasonId);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.id = :seasonId")
	public List<Episode> findBySeason(String seasonId);

	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title = :title")
	public List<Episode> findAllByTvShowTitle(String title);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title = :title AND s.season = :season")
	public List<Episode> findAllByTvShowTitleAndSeason(String title, int season);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title LIKE %:title%")
	public List<Episode> findAllByTvShowTitleLike(String title);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title = :titleTvShow ORDER BY e.rating DESC LIMIT :limit")
	public List<Episode> findAllByOrderByRating(String titleTvShow, int limit);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title = :titleTvShow AND s.season = :season ORDER BY e.rating DESC LIMIT :limit")
	public List<Episode> findAllByOrderByRating(String titleTvShow, int season, int limit);
}
