package org.jkolla.collectors;
import org.jkolla.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Collectors_toMap {
    public static List<Movie> createMovies(){
        return new ArrayList<>(){{
            add(new Movie("Rangasthalam","2018", 8.4,Arrays.asList("Ram Charan","Samantha Ruth Prabhu", "Aadhi", "Jagapathi Babu")));
            add(new Movie("Bharat Ane Nenu","2018", 7.7,Arrays.asList("Mahesh Babu", "Kiara Advani", "Prakash Raj" )));
            add(new Movie("Saaho","2019", 5.2,Arrays.asList("Prabhas", "Shraddha Kapoor", "Jackie Shroff")));
            add(new Movie("Bahubali: The Beginning","2015", 8.1,Arrays.asList( "Prabhas", "Rana Daggubati", "Ramya Krishnan" )));
            add(new Movie("Baahubali 2: The Conclusion","2017", 8.2,Arrays.asList( "Prabhas", "Rana Daggubati", "Anushka Shetty")));
            add(new Movie("Sye Raa Narasimha Reddy","2019", 7.5,Arrays.asList( "Chiranjeevi", "Amitabh Bachchan", "Sudeep" )));
            add(new Movie("Ala Vaikunthapurramuloo","2020", 7.2,Arrays.asList( "Allu Arjun", "Pooja Hegde", "Tabu" )));
            add(new Movie("Nannaku Prematho","2016", 7.6,Arrays.asList( "N.T. Rama Rao Jr.", "Rakul Preet Singh", "Jagapathi Babu" )));
            add(new Movie("Arjun Reddy","2017", 8.1,Arrays.asList( "Vijay Deverakonda", "Shalini Pandey", "Jia Sharma" )));
          //  add(new Movie("Nannaku Prematho","2016", 7.6,Arrays.asList( "N.T. Rama Rao Jr.", "Rakul Preet Singh", "Jagapathi Babu" )));
          //  add(new Movie("Arjun Reddy","2017", 8.1,Arrays.asList( "Vijay Deverakonda", "Shalini Pandey", "Jia Sharma" )));
        }};
    }
    public static void main(String[] args) {
        List<Movie> movies= createMovies();
        movies.forEach(System.out::println);
        System.out.println();
//       public final class Collectors extends Object
//       Implementations of Collector that implement various useful reduction operations,such as accumulating elements into collections, summarizing elements according to various criteria, etc.

//       static <T,K,U> Collector<T,?,Map<K,U>>	toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)
//       Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.

        Map<String , Movie> movieMap= movies.stream()
                .collect(Collectors.toMap(new Function<Movie, String>() {
                    @Override
                    public String apply(Movie movie) {
                        return movie.getMovieName();
                    }
                }, new Function<Movie, Movie>() {
                    @Override
                    public Movie apply(Movie movie) {
                        return movie;
                    }
                }));
        // Provided the valueMapper as Function.identity() which is equivalent as e -> e.
        Map<String, Movie> movieMapCopy= movies.stream()
                .collect(Collectors.toMap(
                        Movie::getMovieName,
                        Function.identity()
                ));
        System.out.println(movieMapCopy);
        System.out.println();
        System.out.println(movieMap);
        System.out.println();
        Map<String, Double> movieRatingMap= movies.stream()
                .collect(Collectors.toMap(Movie::getMovieName,Movie::getRating));
        // Providing the keyMapper and valueMapper using method reference signature
        System.out.println(movieRatingMap);
    }
}
