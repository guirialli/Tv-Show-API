package com.estudos.provider.series.local.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, String> {
	
	public Optional<TvShow> findByTitle(String title);
	
	@Query("SELECT t FROM TvShow t WHERE t.title LIKE %:title%")
	public List<TvShow> findAllByTitle(String title);
}
