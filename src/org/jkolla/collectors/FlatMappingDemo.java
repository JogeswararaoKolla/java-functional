package org.jkolla.collectors;

import org.jkolla.models.Movie;
import org.jkolla.models.MovieMapperFlat;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class FlatMappingDemo {
    public static List<Movie> createMovies(){
        return new ArrayList<>(){{
            add(new Movie("Rangasthalam","2018", 8.4, Arrays.asList("Ram Charan","Samantha Ruth Prabhu", "Aadhi", "Jagapathi Babu")));
            add(new Movie("Bharat Ane Nenu","2018", 7.7,Arrays.asList("Mahesh Babu", "Kiara Advani", "Prakash Raj" )));
            add(new Movie("Saaho","2019", 5.2,Arrays.asList("Prabhas", "Shraddha Kapoor", "Jackie Shroff")));
            add(new Movie("Bahubali: The Beginning","2015", 8.1,Arrays.asList( "Prabhas", "Rana Daggubati", "Ramya Krishnan" )));
            add(new Movie("Baahubali 2: The Conclusion","2017", 8.2,Arrays.asList( "Prabhas", "Rana Daggubati", "Anushka Shetty")));
            add(new Movie("Sye Raa Narasimha Reddy","2019", 7.5,Arrays.asList( "Chiranjeevi", "Amitabh Bachchan", "Sudeep" )));
            add(new Movie("Ala Vaikunthapurramuloo","2020", 7.2,Arrays.asList( "Allu Arjun", "Pooja Hegde", "Tabu" )));
            add(new Movie("Nannaku Prematho","2016", 7.6,Arrays.asList( "N.T. Rama Rao Jr.", "Rakul Preet Singh", "Jagapathi Babu" )));
            add(new Movie("Arjun Reddy","2017", 8.1,Arrays.asList( "Vijay Deverakonda", "Shalini Pandey", "Jia Sharma" )));
        }};
    }
    public static void main(String[] args) {
        List<Movie> movies = createMovies();
        
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
