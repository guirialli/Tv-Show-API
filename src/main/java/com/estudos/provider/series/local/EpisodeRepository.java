package com.estudos.provider.series.local;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, String> {
	public List<Episode> findAllByTitle(String title);
	
	public Optional<Episode> findByEpisodeAndTitle(int episode, String title);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.id = :seasonId")
	public List<Episode> findBySeason(String seasonId);

	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title = :title")
	public List<Episode> findAllByTvShowTitle(String title);
	
	@Query("SELECT e FROM Episode e JOIN FETCH e.season s WHERE s.tvShow.title LIKE %:title%")
	public List<Episode> findAllByTvShowTitleLike(String title);
}
