package at.htl.filmbibliothek.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Film {
    final IntegerProperty length = new SimpleIntegerProperty();
    final IntegerProperty id = new SimpleIntegerProperty();
    final StringProperty name = new SimpleStringProperty();
    final StringProperty producer = new SimpleStringProperty();
    final ObjectProperty<LocalDate> realeaseDate = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public Film() {
    }

    public Film(int id, int length, String name, String producer, LocalDate releaseDate){
        this.idProperty().set(id);
        this.length.set(length);
        this.name.set(name);
        this.producer.set(producer);
        this.realeaseDate.set(releaseDate);
    }

    public Film(Film film){
        this.length.set(film.getLength());
        this.name.set(film.getName());
        this.producer.set(film.getProducer());
        this.realeaseDate.set(film.getRealeaseDate());
    }

    public int getLength() {
        return length.get();
    }

    public IntegerProperty lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getProducer() {
        return producer.get();
    }

    public StringProperty producerProperty() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer.set(producer);
    }

    public LocalDate getRealeaseDate() {
        return realeaseDate.get();
    }

    public ObjectProperty<LocalDate> realeaseDateProperty() {
        return realeaseDate;
    }

    public void setRealeaseDate(LocalDate realeaseDate) {
        this.realeaseDate.set(realeaseDate);
    }

    @Override
    public String toString() {
        return "Film{" +
                "length=" + length +
                ", name=" + name +
                ", producer=" + producer +
                ", realeaseDate=" + realeaseDate +
                '}';
    }
}
