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
        String response = apiReader.getDataFromClientWithTitle("https://api.themoviedb.org/3/search/movie?query=%%&api_key=" + api_key, movie);

        System.out.println(response);

        List<MovieDTO> movieDTO = apiReader.getMovieData(response);
        
        movieDTO.stream().forEach(System.out::println);

    }

}
