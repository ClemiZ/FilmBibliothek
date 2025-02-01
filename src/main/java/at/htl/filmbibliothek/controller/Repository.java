package at.htl.filmbibliothek.controller;

import at.htl.filmbibliothek.model.Film;

import java.util.List;

public interface Repository {
    public void save(Film entity);
    public void insert(Film entity);
    public void update(Film entity);
    public void delete(long id);
    public List<Film> findAll();
    public Film findById(long id);
    List<Film> findByName(String name);
}
