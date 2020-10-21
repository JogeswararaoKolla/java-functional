package org.jkolla.interfaces;

import org.jkolla.models.Movie;
import org.jkolla.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Movie> movies = new Util().createMovies();

        Comparator<Movie> cmpByRating = (t1, t2) -> t1.getRating().compareTo(t2.getRating());

        Function<Movie,Double> byRatingFunction = movie -> movie.getRating();

        Function<Movie,String> byNameFunction = Movie::getMovieName;

        Comparator<Movie> cmpMovieByRating = Comparator.comparing(byRatingFunction);

        Comparator<Movie> cmpMovieByName = Comparator.comparing(byNameFunction);

        Comparator<Movie> cmpByRatingThenByName = cmpMovieByRating.thenComparing(Movie::getMovieName);
        // thenComparing is instance method on the comparator.

        ArrayList<String> collect = movies.stream()
                .sorted(java.util.Comparator.comparing(Movie::getRating).reversed())
                .map(Movie::getMovieName)
                .limit(3)
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
        System.out.println("collect = " + collect);

    }
}
