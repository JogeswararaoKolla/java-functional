package org.jkolla.collectors;

import org.jkolla.models.Movie;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class maxByDemo {
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

        Optional<Movie> maxMovieByRating= movies.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Movie::getRating)));
        System.out.println("maxMovieByRating = " + maxMovieByRating); // maxMovieByRating = Optional[Movie{movieName='Rangasthalam', movieReleaseYear='2018', rating=8.4, actors=[Ram Charan, Samantha Ruth Prabhu, Aadhi, Jagapathi Babu]}]

        System.out.println();
        System.out.println(maxMovieByRating.filter(e -> e.getRating() > 9.0)
                .map(e -> e.getMovieName()).orElseGet(()-> "No Movie with gt 9 rating")); // No Movie with gt 9 rating

//        System.out.println(maxMovieByRating.filter(e -> e.getRating() > 9.0)
//                .map(e -> e.getMovieName()).get()); // java.util.NoSuchElementException: No value present

        if(maxMovieByRating.isPresent()) {
            String movieName = maxMovieByRating.map(e -> e.getMovieName()).get();
            System.out.println("movieName = " + movieName);
        } // to avoid java.util.NoSuchElementException check isPresent()  on Optional before applying get() method

        System.out.println(maxMovieByRating.map(e -> null)); // Optional.empty

//        <U> Optional<U>	map(Function<? super T,? extends U> mapper)
//        If a value is present, apply the provided mapping function to it, and if the result is non-null, return an Optional describing the result.

        System.out.println(maxMovieByRating.map( e -> e.getMovieName())); // Optional[Rangasthalam]
        System.out.println(maxMovieByRating
                .filter( e-> e.getRating() > 9)
                .map( e -> e.getMovieName()));  // Optional.empty

        System.out.println(maxMovieByRating.filter(e -> e.getRating() > 9.0)
                .map(e -> e.getMovieName()).isPresent()); // false

        maxMovieByRating = Optional.empty();
       // static <T> Optional<T>	empty() Returns an empty Optional instance.
        System.out.println(maxMovieByRating.orElseGet(Movie::new)); // Movie{movieName='null', movieReleaseYear='null', rating=null, actors=null}
        System.out.println();

       Map<String, Long> movieMap= movies.stream()
                .flatMap(movie -> movie.getActors().stream())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println("movieMap = " + movieMap);

        Optional<Map.Entry<String, Long>> maxMovieByActors= movieMap.entrySet().stream()
                .collect(Collectors.maxBy(Comparator.comparing(entry -> entry.getValue())));
        System.out.println("maxMovieByActors = " + maxMovieByActors); // maxMovieByActors = Optional[Prabhas=3]

        // Collector is created
        // Building a supplier Interface
        Supplier<List<String>> supplier = () -> new ArrayList<>();
        // Building an accumulator element using BiConsumer Interface
        BiConsumer<List<String>,String> accumulator = (list,e) -> list.add(e);
        // Building an combiner that combines two lists for parallel processing
        BiConsumer<List<String> , List<String> > combiner = (list1, list2 ) -> list1.addAll(list2);

       List<String> moviesList= movies.stream()
                .filter(e -> e.getRating() > 7.5 )
                .map(Movie::getMovieName)
//                .collect(ArrayList::new, List::add, List::addAll); // Using Method reference signature
                .collect(()-> new ArrayList<String>(),(list,e) -> list.add(e),(list1,list2) -> list1.addAll(list2));
//                .collect(Collectors.toList()); // Using Collectors.toList
        System.out.println("moviesList = " + moviesList); // moviesList = [Rangasthalam, Bharat Ane Nenu, Bahubali: The Beginning, Baahubali 2: The Conclusion, Nannaku Prematho, Arjun Reddy]


    }
}
