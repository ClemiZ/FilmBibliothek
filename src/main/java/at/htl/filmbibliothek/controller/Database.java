package at.htl.filmbibliothek.controller;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class Database {

    // getClass().getClassLoader().getResourceAsStream(...) kann nicht verwendet werden,
    // da dies eine statische Variable ist
    //static final String appConfigPath = Database.class.getResource("/database.properties").getPath();
    // Objects.requireNonNull wirft eine NullPointerException, wenn die Datei nicht gefunden wird
    static final String appConfigPath = Objects.requireNonNull(
            Database.class.getResource("/database.properties")
    ).getPath();

    static final String DATABASE = "db";
    static final int PORT = 9092;
    static final String USERNAME = "app";
    static final String PASSWORD = "app";
    public static final String URL = String.format("jdbc:h2:tcp://localhost:%d/%s", PORT, DATABASE);

    /**
     * Get a DataSource object to connect to the database.
     * The properties are read from a file 'database.properties'.
     * If the file is not found, the default properties are used.
     * The default port of the h2 db is 9092
     *
     * @return DataSource object
     */
    public static DataSource getDataSource(){
        Properties dbProperties = new Properties();

        try (FileInputStream in = new FileInputStream(appConfigPath)) {
            dbProperties.load(in);

            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setUser(dbProperties.getProperty("username"));
            dataSource.setPassword(dbProperties.getProperty("password"));
            String url = String.format("jdbc:h2:tcp://localhost:%s/%s"
                    , dbProperties.getProperty("port")
                    , dbProperties.getProperty("database"));
            dataSource.setURL(url);
            System.out.println("Using file 'database.properties': " + dataSource.getURL());
            return dataSource;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setUser(USERNAME);
            dataSource.setPassword(PASSWORD);
            dataSource.setURL(URL);
            System.out.println("Using default database properties: " + dataSource.getURL());
            return dataSource;
        }

    }

}