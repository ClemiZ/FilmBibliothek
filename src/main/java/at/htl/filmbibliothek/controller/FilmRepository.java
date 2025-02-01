package at.htl.filmbibliothek.controller;

import at.htl.filmbibliothek.model.Film;
import org.h2.command.Prepared;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository implements Repository{
    DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Film entity) {
        if(entity.idProperty() == null){
            insert(entity);
        } else{
            update(entity);
        }

    }

    @Override
    public void insert(Film entity) {
        String sql = "INSERT INTO film (f_name, f_length, f_producer, f_releaseDate) values (?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection()
        ;   PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getLength());
            statement.setString(3, entity.getProducer());
            statement.setDate(4, Date.valueOf(entity.getRealeaseDate()));
            if(statement.executeUpdate() == 0){
                System.out.println("insert failed, no lines added");
            }
            try(ResultSet rs = statement.getGeneratedKeys()){
                if(rs.next()){
                    entity.idProperty().set(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Film entity) {
        String sql = "UPDATE film set f_name = ?, f_length = ?, f_producer = ?, f_releaseDate = ? where f_id = "
                + entity.getId();


        try(Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getLength());
            statement.setString(3, entity.getProducer());
            statement.setDate(4, Date.valueOf(entity.getRealeaseDate()));
            if(statement.executeUpdate() == 0){
                System.out.println("Update failed, no liens affected");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM film where f_id = " + id;

        try(Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)){
            if(statement.executeUpdate() == 0){
                System.out.println("Delete failed, no lines affected");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Film> findAll() {
        String sql = "SELECT * from film";
        List<Film> filmList = new ArrayList<>();

        try(Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)){
            try(ResultSet rs = statement.executeQuery()) {
                while(rs.next()){
                    filmList.add(new Film(
                            rs.getInt("f_id"),
                            rs.getInt("f_length"),
                            rs.getString("f_name"),
                            rs.getString("f_producer"),
                            rs.getDate("f_releaseDate").toLocalDate()
                    ));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return filmList;
    }

    @Override
    public Film findById(long id) {
        String sql = "SELECT * FROM film WHERE f_id = " + id;

        try(Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)){
            if(statement.executeUpdate() == 0){
                System.out.println("Search failed, no line found");
            }
            try(ResultSet rs = statement.getResultSet()){
                if (rs.next()){
                    return new Film(
                            rs.getInt("f_id"),
                            rs.getInt("f_length"),
                            rs.getString("f_name"),
                            rs.getString("f_producer"),
                            rs.getDate("f_releaseDate").toLocalDate()
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Film> findByName(String name) {
        String sql = "SELECT * FROM film WHERE f_name LIKE ?";
        List<Film> filmList = new ArrayList<>();

        try(Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, "%" + name + "%");
            if(statement.executeUpdate() == 0){
                System.out.println("Search failed, no line found");
            }
            try(ResultSet rs = statement.getResultSet()){
                while(rs.next()){
                    filmList.add(new Film(
                            rs.getInt("f_id"),
                            rs.getInt("f_length"),
                            rs.getString("f_name"),
                            rs.getString("f_producer"),
                            rs.getDate("f_releaseDate").toLocalDate()
                    ));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filmList;
    }
}
