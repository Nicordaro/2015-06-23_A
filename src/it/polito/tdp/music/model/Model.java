package it.polito.tdp.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.music.db.MusicDAO;

public class Model {

	private MusicDAO dao;
	private List<Artist> top20;
	private List<IDandREP> record;
	private Graph<Country, DefaultWeightedEdge> graph;

	public Model() {
		this.dao = new MusicDAO();
		this.top20 = new ArrayList<>();
		this.record = new ArrayList<>();
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}

	public List<Artist> calcolaTop(int numese) {

		record = dao.getArtistListening(numese);
		Collections.sort(record);

		for (int i = 0; i < 20; i++) {
			IDandREP ir = record.get(i);
			Artist a = dao.getArtist(ir.getArtistId(), ir.getNumberOfListening());
			top20.add(a);
		}

		// DEBUG
		// System.out.println(top20);

		return top20;
	}

	public void creaGrafo() {

		List<Country> countemp = new ArrayList<>();
		for (Artist a : this.top20) {
			countemp.addAll(dao.getCountriesByArtist(a.getId()));
		}

		for (Country c : countemp) {
			if (!graph.vertexSet().contains(c)) {
				graph.addVertex(c);
			}
		}

		for (Country c : graph.vertexSet()) {
			List<Integer> artist1 = new ArrayList<>(dao.getArtistByCountry(c.getId()));
			List<Integer> temp = new ArrayList<>(dao.getArtistByCountry(c.getId()));
			for (Country c2 : graph.vertexSet()) {
				List<Integer> artist2 = new ArrayList<>(dao.getArtistByCountry(c2.getId()));
				if (!artist1.equals(artist2)) {
					temp.retainAll(artist2);
					Graphs.addEdge(this.graph, c, c2, temp.size());
				}
			}
		}

	}

	public int calcolaDistanzaMax() {
		this.creaGrafo();
		// Double distmax = Graphs.getDiameter();

		return 0;
	}

}
