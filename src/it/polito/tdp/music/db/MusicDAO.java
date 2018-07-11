package it.polito.tdp.music.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.music.model.Artist;
import it.polito.tdp.music.model.City;
import it.polito.tdp.music.model.Country;
import it.polito.tdp.music.model.IDandREP;
import it.polito.tdp.music.model.Listening;
import it.polito.tdp.music.model.Track;

public class MusicDAO {

	public List<Country> getAllCountries() {
		final String sql = "SELECT id, country FROM country";

		List<Country> countries = new LinkedList<Country>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				countries.add(new Country(res.getInt("id"), res.getString("country")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return countries;

	}

	public List<IDandREP> getArtistListening(int numese) {
		final String sql = "SELECT DISTINCT artistid, Count(artistid) FROM listening as l WHERE l.month = ?  GROUP BY artistid";

		List<IDandREP> record = new ArrayList<IDandREP>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, numese);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				IDandREP ir = new IDandREP(res.getInt("artistid"), res.getInt("Count(artistid)"));

				record.add(ir);

			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return record;

	}

	public List<City> getAllCities() {
		final String sql = "SELECT id, city FROM city";

		List<City> cities = new LinkedList<City>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				cities.add(new City(res.getInt("id"), res.getString("city")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return cities;

	}

	public List<Artist> getAllArtists() {
		final String sql = "SELECT id, artist FROM artist";

		List<Artist> artists = new LinkedList<Artist>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				artists.add(new Artist(res.getInt("id"), res.getString("artist")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return artists;

	}

	public List<Track> getAllTracks() {
		final String sql = "SELECT id, track FROM track";

		List<Track> tracks = new LinkedList<Track>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				tracks.add(new Track(res.getInt("id"), res.getString("track")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return tracks;

	}

	public List<Listening> getAllListenings() {
		final String sql = "SELECT id, userid, month, weekday, longitude, latitude, countryid, cityid, artistid, trackid FROM listening";

		List<Listening> listenings = new LinkedList<Listening>();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				listenings.add(new Listening(res.getLong("id"), res.getLong("userid"), res.getInt("month"),
						res.getInt("weekday"), res.getDouble("longitude"), res.getDouble("latitude"),
						res.getInt("countryid"), res.getInt("cityid"), res.getInt("artistid"), res.getInt("trackid")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return listenings;

	}

	public static void main(String[] args) {
		MusicDAO dao = new MusicDAO();

		List<Country> countries = dao.getAllCountries();
		// System.out.println(countries) ;

		List<City> cities = dao.getAllCities();
		// System.out.println(cities) ;

		List<Artist> artists = dao.getAllArtists();

		List<Track> tracks = dao.getAllTracks();

		List<Listening> listenings = dao.getAllListenings();

		System.out.format("Loaded %d countries, %d cities, %d artists, %d tracks, %d listenings\n", countries.size(),
				cities.size(), artists.size(), tracks.size(), listenings.size());
	}

	public Artist getArtist(int artistId, int listening) {
		final String sql = "SELECT artist from artist as a where a.id = ?";

		Artist a = new Artist();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, artistId);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				a = new Artist(artistId, res.getString("artist"), listening);
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return a;
	}

	public Artist getArtistById(int artistId) {
		final String sql = "SELECT artist from artist as a where a.id = ?";

		Artist a = new Artist();

		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, artistId);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				a = new Artist(artistId, res.getString("artist"));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return a;
	}

	public List<Country> getCountriesByArtist(int id) {

		final String sql = "SELECT DISTINCT c.country as country FROM listening as l, country as c WHERE l.artistid = ?";

		List<Country> countries = new LinkedList<Country>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				countries.add(new Country(res.getInt("id"), res.getString("country")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return countries;

	}

	public List<Integer> getArtistByCountry(int id) {

		final String sql = "SELECT DISTINCT l.artistid as artistid FROM listening as l, country as c WHERE l.countryid = ?";

		List<Integer> artists = new LinkedList<Integer>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				artists.add(res.getInt("artistid"));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return artists;

	}

}
