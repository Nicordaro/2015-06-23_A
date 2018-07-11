package it.polito.tdp.music.model;

public class Artist {

	private int id;
	private String artist;
	private int listening;

	public Artist(int id, String artist, int listening) {
		super();
		this.id = id;
		this.artist = artist;
		this.listening = listening;
	}

	public Artist(int id, String artist) {
		super();
		this.id = id;
		this.artist = artist;
	}

	public Artist() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getListening() {
		return listening;
	}

	public void setListening(int listening) {
		this.listening = listening;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(artist);
		builder.append(" - ");
		builder.append(listening);
		return builder.toString();
	}

}
