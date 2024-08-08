package com.estudos.provider.series.local;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import java.util.function.Predicate;

import com.estudos.common.convert.DateConvert;
import com.estudos.provider.series.imdb.dto.DataTvShow;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tv_shows")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class TvShow {

	public static final Predicate<String> isNA = str -> str.equalsIgnoreCase("N/A") || str == null;

	public TvShow(DataTvShow imdb) throws ParseException {
		this.id = UUID.randomUUID().toString();
		this.title = imdb.title();
		this.actors = imdb.actors();
		this.description = imdb.description();
		this.genre = imdb.genre();
		this.writer = imdb.writer();

		if (!isNA.test(imdb.seasons()))
			this.totalSeasons = Integer.parseInt(imdb.seasons());
		if (!isNA.test(imdb.rating()))
			this.rating = Double.parseDouble(imdb.rating());
		if (!isNA.test(imdb.year())) {
			this.year = DateConvert.imdbYear.parse(imdb.year());
		}
		if (!isNA.test(imdb.released())) {
			this.released = DateConvert.imdbRelesed.parse(imdb.released());
		}

	}

	@Id
	@Column(nullable = false)
	private String id;

	@Column(unique = true, nullable = false)
	private String title;

	@Column
	private String description;

	@Column(name = "total_seasons")
	private Integer totalSeasons;

	@Column(name = "year")
	private Date year;

	@Column(name = "released")
	private Date released;

	@Column(name = "genre")
	private String genre;

	@Column(name = "actors")
	private String actors;

	@Column
	private String writer;

	@Column
	private Double rating;
}
