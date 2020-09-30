package org.jkolla.generics;

import org.jkolla.models.*;

import java.util.*;

public class StreamMaxDemo {
    public static void main(String[] args) {
        // Declare a movie object and initialize the actors with empty ArrayList
        Movie movie = new Movie("Rangasthalam","2018", 8.4,new ArrayList<>());
        movie.addActor("Jogi");
        movie.addActor("Sumarchitha");
        // Calling the addActor method on movie object to add actor names
        System.out.println(movie);
        System.out.println();
        List<Movie> movies= new ArrayList<>(){{
            add(new Movie("Rangasthalam","2018", 8.4,Arrays.asList("Ram Charan","Samantha Ruth Prabhu", "Aadhi", "Jagapathi Babu")));
            add(new Movie("Bharat Ane Nenu","2018", 7.7,Arrays.asList("Mahesh Babu", "Kiara Advani", "Prakash Raj" )));
            add(new Movie("Saaho","2019", 5.2,Arrays.asList("Prabhas", "Shraddha Kapoor", "Jackie Shroff")));
            add(new Movie("Bahubali: The Beginning","2015", 8.1,Arrays.asList( "Prabhas", "Rana Daggubati", "Ramya Krishnan" )));
            add(new Movie("Baahubali 2: The Conclusion","2017", 8.2,Arrays.asList( "Prabhas", "Rana Daggubati", "Anushka Shetty")));
            add(new Movie("Sye Raa Narasimha Reddy","2010", 7.5,Arrays.asList( "Chiranjeevi", "Amitabh Bachchan", "Sudeep" )));
            add(new Movie("Ala Vaikunthapurramuloo","2020", 7.2,Arrays.asList( "Allu Arjun", "Pooja Hegde", "Tabu" )));
            add(new Movie("Nannaku Prematho","2016", 7.6,Arrays.asList( "N.T. Rama Rao Jr.", "Rakul Preet Singh", "Jagapathi Babu" )));
            add(new Movie("Arjun Reddy","2017", 8.1,Arrays.asList( "Vijay Deverakonda", "Shalini Pandey", "Jia Sharma" )));
        }};

        movies.add(new Movie("Nannaku Prematho","2016", 7.6,Arrays.asList( "N.T. Rama Rao Jr.", "Rakul Preet Singh", "Jagapathi Babu" )));
        movies.add(new Movie("Arjun Reddy","2017", 8.1,Arrays.asList( "Vijay Deverakonda", "Shalini Pandey", "Jia Sharma" )));
//
//        List<Movie> movies2=Arrays.asList(new Movie("Bharat Ane Nenu","2018", 7.7,Arrays.asList("Mahesh Babu", "Kiara Advani", "Prakash Raj" )));
//        movies2.add(new Movie("Arjun Reddy","2017", 8.1,Arrays.asList( "Vijay Deverakonda", "Shalini Pandey", "Jia Sharma" )));
        movies.forEach(System.out::println);
        System.out.println();
        Optional<Movie> maxRating =
        movies.stream()
                .max(new Comparator<Movie>() {
                    @Override
                    public int compare(Movie o1, Movie o2) {
                        return (int) (o1.getRating() - o2.getRating());
                    }
                });
        Optional<Movie> maxMovieName = movies.stream()
                .max(new Comparator<Object>() {
                    @Override
                    public int compare(Object o1,Object o2) {
                        return o1.toString().compareTo(o2.toString());
                    }
                });
        maxMovieName = movies.stream()
                .max(Comparator.comparing(Movie::getMovieName));

        maxRating = movies.stream()
                .max(Comparator.comparingDouble(Movie::getRating));

//        Optional<T>	max(Comparator<? super T> comparator)
//        Returns the maximum element of this stream according to the provided Comparator.

        System.out.println(maxRating);
        System.out.println();
        System.out.println(maxMovieName);

    }
}
