package it.polito.tdp.music;

import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.music.model.Artist;
import it.polito.tdp.music.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MusicController {

	private Model model;
	private List<Artist> top20;
	private int distMax = 0;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxMese"
	private ComboBox<Month> boxMese; // Value injected by FXMLLoader

	@FXML // fx:id="btnArtisti"
	private Button btnArtisti; // Value injected by FXMLLoader

	@FXML // fx:id="btnNazioni"
	private Button btnNazioni; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void calcolaDistanza(ActionEvent event) {
		txtResult.clear();
		if (this.top20.size() == 0) {
			txtResult.appendText("ERRORE! SELEZIONARE UN MESE PRIMA!");
		}

		else {
			this.distMax = model.calcolaDistanzaMax();
		}

	}

	@FXML
	void calcolaTop(ActionEvent event) {
		txtResult.clear();

		Month mese = boxMese.getValue();
		int numese = mese.getValue();

		this.top20 = model.calcolaTop(numese);
		txtResult.appendText("Top 20 Artists of " + mese.toString() + "\n");

		for (Artist a : this.top20) {
			txtResult.appendText(a.toString() + "\n");
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert btnArtisti != null : "fx:id=\"btnArtisti\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert btnNazioni != null : "fx:id=\"btnNazioni\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MusicA.fxml'.";
	}

	public void setModel(Model model) {
		this.model = model;
		this.top20 = new ArrayList<>();

		for (int mese = 1; mese <= 12; mese++) {
			boxMese.getItems().add(Month.of(mese));
		}
	}
}
