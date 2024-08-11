package com.estudos.provider.series.local.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, String>{
	public List<Season> findByTitle(String title);
	
	public Optional<Season> findByTitleAndSeason(String title, int season);
	
	@Query("SELECT s FROM Season s JOIN FETCH s.tvShow t WHERE t.id = :tvShowId")
	public List<Season> findByTvShow(String tvShowId);
	
	@Query("SELECT s FROM Season s WHERE s.title LIKE %:title%")
	public List<TvShow> findAllByLikeTitle(String title);
}
