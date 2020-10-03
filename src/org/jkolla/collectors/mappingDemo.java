package org.jkolla.collectors;

import org.jkolla.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class mappingDemo {
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
        List<Movie> movies= createMovies();
        Map<String, List<Movie>> moviesByReleaseYear = movies.stream()
                .collect(Collectors.groupingBy(Movie::getMovieReleaseYear));
        System.out.println("moviesByReleaseYear = " + moviesByReleaseYear);

        Map<String, List<Double>> moviesByReleaseYear2 = movies.stream()
                .collect(Collectors.groupingBy(Movie::getMovieReleaseYear,
                        Collectors.mapping(e -> e.getRating(),
                                Collectors.toList())));
        // groupingBy takes (Function classifier ,Collector)
        // mapping takes (Function mapper ,Collector)
        System.out.println("moviesByReleaseYear2 = " + moviesByReleaseYear2); // moviesByReleaseYear2 = {2019=[5.2, 7.5], 2018=[8.4, 7.7], 2017=[8.2, 8.1], 2016=[7.6], 2015=[8.1], 2020=[7.2]}

        // Re-mapping using the toMap Collector
        // toMap do not accepts duplicates and it throws IllegalStateException
        // static <T,K,U> Collector<T,?,Map<K,U>>	toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)
        // Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.

        Map<String, List<Double>> moviesByReleaseYear3 = movies.stream()
                .collect(Collectors.groupingBy(Movie::getMovieReleaseYear))  // Map<String, List<Movie>>
                .entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream().map(e -> e.getRating()).collect(Collectors.toList())
                ));
        System.out.println("moviesByReleaseYear3 = " + moviesByReleaseYear3);
        
    }
}
