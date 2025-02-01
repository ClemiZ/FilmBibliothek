package at.htl.filmbibliothek.view;

import at.htl.filmbibliothek.model.Film;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FilmView extends VBox {
    ListView<Film> films = new ListView<>();

    TextField idInput = new TextField();
    TextField nameInput = new TextField();
    TextField producerInput = new TextField();
    TextField lengthInput = new TextField();
    DatePicker releaseDateInput = new DatePicker();

    public FilmView() {
        idInput.setEditable(false);
        this.getChildren().addAll(films, nameInput, producerInput, lengthInput, releaseDateInput);
    }

    public ListView<Film> getFilms() {
        return films;
    }

    public void setFilms(ListView<Film> films) {
        this.films = films;
    }

    public TextField getNameInput() {
        return nameInput;
    }

    public void setNameInput(TextField nameInput) {
        this.nameInput = nameInput;
    }

    public TextField getProducerInput() {
        return producerInput;
    }

    public void setProducerInput(TextField producerInput) {
        this.producerInput = producerInput;
    }

    public TextField getLengthInput() {
        return lengthInput;
    }

    public void setLengthInput(TextField lengthInput) {
        this.lengthInput = lengthInput;
    }

    public DatePicker getReleaseDateInput() {
        return releaseDateInput;
    }

    public void setReleaseDateInput(DatePicker releaseDateInput) {
        this.releaseDateInput = releaseDateInput;
    }
}
