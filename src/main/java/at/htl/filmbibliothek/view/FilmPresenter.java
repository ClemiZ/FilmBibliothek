package at.htl.filmbibliothek.view;

import at.htl.filmbibliothek.controller.FilmRepository;
import at.htl.filmbibliothek.model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.NumberStringConverter;

public class FilmPresenter {
    Film film = new Film();
    FilmView view = new FilmView();

    FilmRepository repository = new FilmRepository();
    ObservableList filmList = FXCollections.observableArrayList();


    void bindModelToView(){
        view.idInput.textProperty().bindBidirectional(film.idProperty(), new NumberStringConverter());
        film.nameProperty().bindBidirectional(view.nameInput.textProperty());
        film.producerProperty().bindBidirectional(view.producerInput.textProperty());
        film.realeaseDateProperty().bindBidirectional(view.releaseDateInput.valueProperty());
        view.lengthInput.textProperty().bindBidirectional(film.lengthProperty(), new NumberStringConverter());
    }
}
