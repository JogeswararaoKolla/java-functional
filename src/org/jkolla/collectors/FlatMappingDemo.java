package org.jkolla.collectors;

import org.jkolla.models.Movie;
import org.jkolla.models.MovieMapperFlat;
import org.jkolla.utils.Util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class FlatMappingDemo {
    public static void main(String[] args) {
        List<Movie> movies = (new Util()).createMovies();
        
        // Max no of movies by Actors 
        Optional<Map.Entry<String, Long>> movieActorsCnt = movies.stream()
                .collect(
                        flatMapping(
                                movie -> movie.getActors().stream(),
                                groupingBy(Function.identity(),
                                        counting())
                        ) // Set<Map<K,V>.Entry<String,Long>
                ).entrySet()
                .stream()
                .collect(
                        maxBy(Comparator.comparing(entry -> entry.getValue()))
                );
        System.out.println("movieActorsCnt = " + movieActorsCnt); // movieActorsCnt = Optional[Prabhas=3]

        // Transform Collection<Movies> to Collection<MovieMapperFlat> using flatMapping method on Collector

        List<MovieMapperFlat> movieMapperFlatList = movies.stream()
                .collect(
                        flatMapping(movie -> movie.getActors().stream()
                                        .map(actor -> new MovieMapperFlat(movie.getMovieName(), Integer.valueOf(movie.getMovieReleaseYear()), movie.getRating(), actor)),
                                toList()
                        ));
        System.out.println("movieMapperFlatList = " + movieMapperFlatList);
        movieMapperFlatList.forEach(System.out::println);

        // Transform Collection<Movies> to Collection<MovieMapperFlat> using flatMap method on stream
        List<MovieMapperFlat> movieMapperFlatList2 = movies.stream()
                .flatMap(movie -> movie.getActors().stream()
                        .map(actor -> new MovieMapperFlat(movie.getMovieName(), Integer.valueOf(movie.getMovieReleaseYear()), movie.getRating(), actor)))
                .collect(Collectors.toList());
        System.out.println("movieMapperFlatList2 = " + movieMapperFlatList2);


        // MovieMapperFlat  list by Actors
        Map<String, List<MovieMapperFlat>> moviesByActorList = movies.stream()
                .collect(
                        flatMapping(movie -> movie.getActors().stream()
                                        .map(actor -> new MovieMapperFlat(movie.getMovieName(), Integer.valueOf(movie.getMovieReleaseYear()), movie.getRating(), actor)),
                                groupingBy(MovieMapperFlat::getActor)
                        ));

        System.out.println("moviesByActorList = " + moviesByActorList);

        // Movie Names by Actors 
        Map<String, List<String>> movieNamesByActorList = movies.stream()
                .collect(
                        flatMapping(movie -> movie.getActors().stream()
                                        .map(actor -> new MovieMapperFlat(movie.getMovieName(), Integer.valueOf(movie.getMovieReleaseYear()), movie.getRating(), actor)),
                                groupingBy(MovieMapperFlat::getActor,
                                        mapping(e -> e.getMovieName(),
                                                toList()))
                        ));
        System.out.println("movieNamesByActorList = " + movieNamesByActorList);
        movieNamesByActorList.forEach((key,value) ->
                System.out.println("key:" + key +";Value:" + value));
    }
}
