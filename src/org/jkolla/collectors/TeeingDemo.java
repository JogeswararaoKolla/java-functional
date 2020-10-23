package org.jkolla.collectors;

import org.jkolla.models.Movie;
import org.jkolla.utils.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.* ;

public class TeeingDemo {
    public static void main(String[] args) {
        List<Movie> movies = new Util().createMovies();

        Map<String, List<String>> collect = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        mapping(e -> e.getMovieName(),
                                toList())));
        System.out.println("collect = " + collect);

        Map<String, Long> collect1 = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        counting()));
        System.out.println("collect1 = " + collect1);

        Map<String, Double> collect2 = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        summingDouble(Movie::getRating)));
        System.out.println("collect2 = " + collect2);
/*
 Returns a Collector that is a composite of two downstream collectors.
 Every element passed to the resulting collector is processed by both downstream collectors,
 then their results are merged using the specified merge function into the final result.
 Returns: a Collector which aggregates the results of two supplied collectors.
        public static <T, R1, R2, R>
                Collector<T, ?, R> teeing(Collector<? super T, ?, R1> downstream1,
                Collector<? super T, ?, R2> downstream2,
                BiFunction<? super R1, ? super R2, R> merger)
*/
        record MovieNamesCountAndRatings(List<String> moviesNames,long count, double ratings) {}

        Map<String, MovieNamesCountAndRatings> collect3 = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        teeing(mapping(e -> e.getMovieName(), toList()), // teeing returns a Collector which aggregates the results of two supplied collectors.
                                summingDouble(Movie::getRating),
                                (movieNames, sumOfRatings) -> new MovieNamesCountAndRatings(movieNames, movieNames.size(), sumOfRatings)
                        )));
        System.out.println("collect3 = " + collect3);

        record CountAndRatings(long count, Double ratings) {}

        Map<String, MovieNamesCountAndRatings> collect4 = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        teeing(mapping(e -> e.getMovieName(), toList()),
                                teeing(counting(),
                                        summingDouble(e -> e.getRating()),
                                        (countTotal, sumOfRatings) -> new CountAndRatings(countTotal, sumOfRatings)),
                                (movieNames, countAndRatings) -> new MovieNamesCountAndRatings(movieNames, countAndRatings.count, countAndRatings.ratings)
                        )));
        System.out.println("collect4 = " + collect4);

        System.out.println("collect4.equals(collect3): " + collect4.equals(collect3)); // collect4.equals(collect3): true
        
        record MovieRatings( Movie movieByMaxRating, Movie movieByMinRating) {
            MovieRatings (){
                this( null,null);
            }
        }

        MovieRatings collect5 = movies.stream()
                .collect(
                        teeing(maxBy(Comparator.comparing(Movie::getRating)),
                                minBy(Comparator.comparing(Movie::getRating)),
                                (max, min) -> max.isPresent() && min.isPresent() ? new MovieRatings(max.get(), min.get()) : new MovieRatings()
                        )
                );
        System.out.println("collect5 = " + collect5); // collect5 = MovieRatings[movieByMaxRating=Movie{movieName='Rangasthalam', movieReleaseYear='2018', rating=8.4, actors=[Ram Charan, Samantha Ruth Prabhu, Aadhi, Jagapathi Babu]}, movieByMinRating=Movie{movieName='Saaho', movieReleaseYear='2019', rating=5.2, actors=[Prabhas, Shraddha Kapoor, Jackie Shroff]}]

    }
}
