package com.example.movielibrary;

import java.io.Serializable;
import java.util.Objects;

public class Movies implements Serializable {

    public  static final String TABLE_NAME = "mm";

    public  static final String COLUMN_ID = "movie_id";
    public  static final String COLUMN_NAME = "movie_name";
    public  static final String COLUMN_DESCRIPTION = "movie_description";
    public  static final String COLUMN_RATING = "movie_rating";
    public  static final String COLUMN_STATUS = "movie_status";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_RATING + " INTEGER, " +
                    COLUMN_STATUS + " INTEGER)";



    String movieName;
    String description;
    int movieRating;
    int movieID;
    boolean isActive;
    static int last_movieID=0;

    public Movies() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Movies(String movieName, String description, int movieRating, boolean isActive) {
        this.movieName = movieName;
        this.movieRating = movieRating;
        this.description = description;
        this.isActive = isActive;
        this.last_movieID++;
        this.movieID = this.last_movieID;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movieName='" + movieName + '\'' +
                ", description='" + description + '\'' +
                ", movieRating=" + movieRating +
                ", movieID=" + movieID +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return movieRating == movies.movieRating &&
                movieID == movies.movieID &&
                isActive == movies.isActive &&
                Objects.equals(movieName, movies.movieName) &&
                Objects.equals(description, movies.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, description, movieRating, movieID, isActive);
    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

}

