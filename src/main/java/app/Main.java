package app;

import app.DTOs.MovieDTO;
import app.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ApiReader apiReader = new ApiReader();

        String api_key = System.getenv("api_key");

        String movie = "Lord of the rings";
        String releaseYear = "2001";
        String response = apiReader.getDataFromClientWithTitle("https://api.themoviedb.org/3/search/movie?query=%%&api_key=" + api_key, movie);
        String response2 = apiReader.getDataFromClientWithTitleAndYear("https://api.themoviedb.org/3/search/movie?query=%%&primary_release_year=##&api_key="+api_key, movie, releaseYear);

        //System.out.println(response);
        //System.out.println(response2);


        List<MovieDTO> movieDTO = apiReader.getMovieData(response);
        //  movieDTO.stream().forEach(System.out::println);

        List<MovieDTO> movieDTO2 = apiReader.getMovieData(response2);
        movieDTO2.stream().forEach(System.out::println);


    }

}
