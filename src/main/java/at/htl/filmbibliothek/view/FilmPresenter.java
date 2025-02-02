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

    public FilmPresenter() {
        bindModelToView();
    }
    public void update(){
        if(view.getFilms().getSelectionModel().getSelectedItem() != null){
            view.getFilms().getSelectionModel().getSelectedItem().setLength(film.getLength());
            view.getFilms().getSelectionModel().getSelectedItem().setName(film.getName());
            view.getFilms().getSelectionModel().getSelectedItem().setProducer(film.getProducer());
            view.getFilms().getSelectionModel().getSelectedItem().setRealeaseDate(film.getRealeaseDate());
            repository.update(view.getFilms().getSelectionModel().getSelectedItem());
        }
    }
    public void delete(){
        if(view.getFilms().getSelectionModel().getSelectedItem() != null){
            filmList.remove(view.getFilms().getSelectionModel().getSelectedItem());
            repository.delete(view.getFilms().getSelectionModel().getSelectedItem().getId());
        }
    }
}
