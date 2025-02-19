package app;

import app.DTOs.MovieDTO;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ApiReader apiReader = new ApiReader();

        String api_key = System.getenv("api_key");

        String movie = "Lord of the rings";
        String releaseYear = "2001";
        String imbdID = "tt0120737";
        String response = apiReader.getDataFromClientWithTitle("https://api.themoviedb.org/3/search/movie?query=%%&api_key=" + api_key, movie);
        String response2 = apiReader.getDataFromClientWithTitleAndYear("https://api.themoviedb.org/3/search/movie?query=%%&primary_release_year=##&api_key="+api_key, movie, releaseYear);
        String response3 = apiReader.getDataFromClientWithID("https://api.themoviedb.org/3/find/%%?external_source=imdb_id&api_key="+api_key,imbdID);

        //System.out.println(response);
        //System.out.println(response2);


        List<MovieDTO> movieDTO = apiReader.getMovieDataBySearch(response);
        //  movieDTO.stream().forEach(System.out::println);

        List<MovieDTO> movieDTO2 = apiReader.getMovieDataBySearch(response2);
        //movieDTO2.stream().forEach(System.out::println);

        MovieDTO movieDTO3 = apiReader.getMovieDataFindByID(response3);
        System.out.println(movieDTO3);

        System.out.println(movieDTO3.getReleaseYear(movieDTO3.getReleaseDate()));





    }

}
