package com.estudos.provider.series.local;

import java.util.UUID;

import com.estudos.provider.series.imdb.dto.DataSeason;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name =  "season")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Season {
	
	public Season(DataSeason imdb) {
		this.id = UUID.randomUUID().toString();
		this.title = imdb.title();
		if(TvShow.isNA.test(imdb.season())) {
			this.season = Integer.parseInt(imdb.season());
		}
	}
	
	public Season(DataSeason imdb, TvShow tvShow) {
		this.id = UUID.randomUUID().toString();
		this.title = imdb.title();
		if(TvShow.isNA.test(imdb.season())) {
			this.season = Integer.parseInt(imdb.season());
		}
		this.tvShow = tvShow;
	}
	
	@Id
	@Column(nullable = false)
	private String id; 
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Integer season;
	
	@ManyToOne
	@JoinColumn(name = "tv_show_id", nullable = false)
	TvShow tvShow;
	
}
