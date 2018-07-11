package it.polito.tdp.music.model;

import java.util.ArrayList;
import java.util.List;

public class CountryAndArtist {

	private Country c;

	private List<Artist> artistList;

	public CountryAndArtist(Country c) {
		this.c = c;
		artistList = new ArrayList<>();
	}

	public void aggiungiArtista(Artist a) {
		artistList.add(a);
	}

	public Country getC() {
		return c;
	}

	public List<Artist> getArtistList() {
		return artistList;
	}

}
