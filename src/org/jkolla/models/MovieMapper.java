package org.jkolla.models;

public class MovieMapper {
    private String movieName;
    private Integer movieReleaseYear;
    private Double rating;

    public MovieMapper(String movieName, Integer movieReleaseYear, Double rating) {
        this.movieName = movieName;
        this.movieReleaseYear = movieReleaseYear;
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "MovieMapper{" +
                "movieName='" + movieName + '\'' +
                ", movieReleaseYear=" + movieReleaseYear +
                ", rating=" + rating +
                '}';
    }

    public MovieMapper() {
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
}
