package org.jkolla.collectors;

import org.jkolla.models.Movie;
import org.jkolla.utils.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoiningDemo {
    public static void main(String[] args) {
        Util util = new Util();
        System.out.println(util.getClass());
        List<Movie> movies = util.createMovies();

        // Movie Names which are released after 2017
        String movieNames = movies.stream()
                .filter(movie -> Integer.valueOf(movie.getMovieReleaseYear()) > 2017)
                .map(movie -> movie.getMovieName())
                .collect(Collectors.joining("-", "@", "@"));
        System.out.println("movieNames = " + movieNames);
        // movieNames = @Rangasthalam-Bharat Ane Nenu-Saaho-Sye Raa Narasimha Reddy-Ala Vaikunthapurramuloo@
        String[] split = movieNames.replaceAll("@", "").split("-");
        System.out.println("split = " + Arrays.toString(split));
       // split = [Rangasthalam, Bharat Ane Nenu, Saaho, Sye Raa Narasimha Reddy, Ala Vaikunthapurramuloo]
    }
}
