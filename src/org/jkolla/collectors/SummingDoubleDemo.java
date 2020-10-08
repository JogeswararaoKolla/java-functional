package org.jkolla.collectors;

import org.jkolla.models.Movie;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class SummingDoubleDemo {
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

        Map<String, List<String>> collect1 = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        mapping(e -> e.getMovieName(),
                                Collectors.toList())));
        System.out.println("collect1 = " + collect1);

        Map<String, Long> collect2 = movies.stream()
                .collect(groupingBy(movie -> movie.getMovieReleaseYear(),
                        counting()));
        System.out.println("collect2 = " + collect2);

        Map<String, String> collect3 = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        collectingAndThen(summingDouble(Movie::getRating),
                            e -> (new DecimalFormat("#.00").format(e))
                        )));
        System.out.println("collect3 = " + collect3);

        Map<String, Double> collect4 = movies.stream()
                .collect(groupingBy(Movie::getMovieReleaseYear,
                        collectingAndThen(summingDouble(Movie::getRating),
                                e -> e) // Here Finisher function we no need to do any transformation as summingDouble returns Double
                        ));
        System.out.println("collect4 = " + collect4);

    }
}
