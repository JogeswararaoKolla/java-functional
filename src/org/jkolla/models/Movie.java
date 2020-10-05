package org.jkolla.models;

import java.util.List;

public class Movie {

    private String movieName;
    private String movieReleaseYear;
    private Double rating;
    private List<String> actors;

    public Movie(String movieName, String movieReleaseYear, Double rating, List<String> actors) {
        this.movieName = movieName;
        this.movieReleaseYear = movieReleaseYear;
        this.rating = rating;
        this.actors = actors;
    }

    public Movie() {
    }

    public String getMovieName() {
        return movieName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public void setMovieReleaseYear(String movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void addActor(String actor){
        this.actors.add(actor);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + "'" +
                ", movieReleaseYear='" + movieReleaseYear + "'" +
                ", rating=" + rating +
                ", actors=" + actors +
                '}';
    }
}
