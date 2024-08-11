package com.estudos.provider.series.local.entity;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.estudos.common.convert.DateConvert;
import com.estudos.provider.series.imdb.dto.DataEpisodes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "episodes")
@NoArgsConstructor
@Getter
@Setter
public class Episode {
	
	public Episode(DataEpisodes imdb) throws ParseException {
		this.id = UUID.randomUUID().toString();
		this.title = imdb.title();
		this.description = imdb.description();
		this.runtime = imdb.runtime();

		if (!TvShow.isNA.test(imdb.rating()))
			this.rating = Double.parseDouble(imdb.rating());
		if (!TvShow.isNA.test(imdb.year())) {
			this.year = DateConvert.imdbYear.parse(imdb.year());
		}
		if (!TvShow.isNA.test(imdb.released())) {
			this.released = DateConvert.imdbRelesed.parse(imdb.released());
		}
		if(!TvShow.isNA.test(imdb.episode())) {
			this.episode = Integer.parseInt(imdb.episode());
		}
	}
	
	public Episode(DataEpisodes imdb, Season season) throws ParseException {
		this.id = UUID.randomUUID().toString();
		this.title = imdb.title();
		this.description = imdb.description();
		this.runtime = imdb.runtime();

		if (!TvShow.isNA.test(imdb.rating()))
			this.rating = Double.parseDouble(imdb.rating());
		if (!TvShow.isNA.test(imdb.year())) {
			this.year = DateConvert.imdbYear.parse(imdb.year());
		}
		if (!TvShow.isNA.test(imdb.released())) {
			this.released = DateConvert.imdbRelesed.parse(imdb.released());
		}
		if(!TvShow.isNA.test(imdb.episode())) {
			this.episode = Integer.parseInt(imdb.episode());
		}
		
		this.season = season;
	}

	@Id
	@Column(nullable = false)
	private String id;

	@Column(nullable = false)
	private String title;

	@ManyToOne
	@JoinColumn(nullable = false, name = "season_id")
	private Season season;

	@Column(nullable = false)
	private Integer episode;

	@Column(nullable = false)
	private String description;

	@Column
	private Date year;

	@Column
	private Date released;

	@Column
	private String runtime;

	@Column
	private Double rating;

}
