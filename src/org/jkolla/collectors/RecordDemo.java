package org.jkolla.collectors;

import org.jkolla.models.Movie;
import org.jkolla.utils.Util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordDemo {
    public static void main(String[] args) {

        record MovieNameAndRatings(String movieName, Double Rating) {
            MovieNameAndRatings() {
                this("default",null); // creating custom constructor
            }
        } // Java 14 preview feature

        List<Movie> movies = new Util().createMovies();

        MovieNameAndRatings movieNameAndRatings = new MovieNameAndRatings();
        System.out.println("movieNameAndRatings = " + movieNameAndRatings); // movieNameAndRatings = MovieNameAndRatings[movieName=default, Rating=null]

        Map<String, List<MovieNameAndRatings>> collect = movies.stream()
                .filter( e -> Integer.parseInt(e.getMovieReleaseYear()) > 2018)
                .collect(Collectors.groupingBy(Movie::getMovieReleaseYear,
                        Collectors.mapping(e -> new MovieNameAndRatings(e.getMovieName(), e.getRating()), // map input to new record object MovieNameAndRatings
                                Collectors.toList())));
        System.out.println("collect = " + collect);


    }
}
