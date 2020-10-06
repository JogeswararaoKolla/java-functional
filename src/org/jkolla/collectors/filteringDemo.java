package org.jkolla.collectors;

import org.jkolla.models.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class filteringDemo {
    public static List<Movie> createMovies() {
        return new ArrayList<>() {{
            add(new Movie("Rangasthalam", "2018", 8.4, Arrays.asList("Ram Charan", "Samantha Ruth Prabhu", "Aadhi", "Jagapathi Babu")));
            add(new Movie("Bharat Ane Nenu", "2018", 7.7, Arrays.asList("Mahesh Babu", "Kiara Advani", "Prakash Raj")));
            add(new Movie("Saaho", "2019", 5.2, Arrays.asList("Prabhas", "Shraddha Kapoor", "Jackie Shroff")));
            add(new Movie("Bahubali: The Beginning", "2015", 8.1, Arrays.asList("Prabhas", "Rana Daggubati", "Ramya Krishnan")));
            add(new Movie("Baahubali 2: The Conclusion", "2017", 8.2, Arrays.asList("Prabhas", "Rana Daggubati", "Anushka Shetty")));
            add(new Movie("Sye Raa Narasimha Reddy", "2019", 7.5, Arrays.asList("Chiranjeevi", "Amitabh Bachchan", "Sudeep")));
            add(new Movie("Ala Vaikunthapurramuloo", "2020", 7.2, Arrays.asList("Allu Arjun", "Pooja Hegde", "Tabu")));
            add(new Movie("Nannaku Prematho", "2016", 7.6, Arrays.asList("N.T. Rama Rao Jr.", "Rakul Preet Singh", "Jagapathi Babu")));
            add(new Movie("Arjun Reddy", "2017", 8.1, Arrays.asList("Vijay Deverakonda", "Shalini Pandey", "Jia Sharma")));
        }};
    }

    public static void main(String[] args) {
        List<Movie> movies = createMovies();
//        Get max Movie grouped by Year and maxBy Rating
//        public static <T,A,R> Collector<T,?,R> filtering​(Predicate<? super T> predicate, Collector<? super T,A,R> downstream)
//        Adapts a Collector to one accepting elements of the same type T by applying the predicate to each input element and only accumulating if the predicate returns true.
        Map<String, Optional<String>> movieByYearRating = movies.stream()
                .collect(Collectors.groupingBy(Movie::getMovieReleaseYear,
                        Collectors.filtering(e -> Integer.valueOf(e.getMovieReleaseYear()) > 2017,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Movie::getRating)),
                                        movie -> movie.map(e -> e.getMovieName())
                                ))));
        System.out.println("movieByYearRating = " + movieByYearRating);
        // movieByYearRating = {2019=Optional[Sye Raa Narasimha Reddy], 2018=Optional[Rangasthalam], 2017=Optional.empty, 2016=Optional.empty, 2015=Optional.empty, 2020=Optional[Ala Vaikunthapurramuloo]
    }
}