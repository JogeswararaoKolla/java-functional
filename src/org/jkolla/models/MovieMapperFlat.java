package org.jkolla.models;

public class MovieMapperFlat {
    private String movieName;
    private Integer movieReleaseYear;
    private Double rating;
    private String actor;

    public MovieMapperFlat() {
    }

    public MovieMapperFlat(String movieName, Integer movieReleaseYear, Double rating, String actor) {
        this.movieName = movieName;
        this.movieReleaseYear = movieReleaseYear;
        this.rating = rating;
        this.actor = actor;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public void setMovieReleaseYear(Integer movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "MovieMapperFlat{" +
                "movieName='" + movieName + '\'' +
                ", movieReleaseYear=" + movieReleaseYear +
                ", rating=" + rating +
                ", actor='" + actor + '\'' +
                '}';
    }
}
