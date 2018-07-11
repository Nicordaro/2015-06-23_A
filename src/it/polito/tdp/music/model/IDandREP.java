package it.polito.tdp.music.model;

public class IDandREP implements Comparable<IDandREP> {

	private int artistId;
	private int numberOfListening;

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public int getNumberOfListening() {
		return numberOfListening;
	}

	public void setNumberOfListening(int numberOfListening) {
		this.numberOfListening = numberOfListening;
	}

	public IDandREP(int artistId, int numberOfListening) {
		this.artistId = artistId;
		this.numberOfListening = numberOfListening;
	}

	@Override
	public int compareTo(IDandREP o) {
		return o.getNumberOfListening() - this.numberOfListening;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IDandREP [artistId=");
		builder.append(artistId);
		builder.append(", numberOfListening=");
		builder.append(numberOfListening);
		builder.append("]");
		return builder.toString();
	}

}
