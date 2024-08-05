package com.estudos.provider.series.imdb;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.estudos.provider.series.imdb.dto.DataEpisodes;

@Service
public class ImdbSort {
	// Atributo para ordenar EPS por avaliação
	public final Comparator<DataEpisodes> EPISODE_COMPARATOR = Comparator.comparingDouble(ep -> {
		try {
			return Double.parseDouble(ep.rating());
		} catch (Exception e) {
			return 0.0;
		}
	});
}
