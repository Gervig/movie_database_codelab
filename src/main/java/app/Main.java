package app;

import app.DTOs.MovieDTO;

import java.util.Date;
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
        Float lowerRating = 8.50F;
        Float upperRating = 9.00F;

        String response1 = apiReader.getDataFromClientWithTitle("https://api.themoviedb.org/3/search/movie?query=%%&api_key=" + api_key, movie);
        String response2 = apiReader.getDataFromClientWithTitleAndYear("https://api.themoviedb.org/3/search/movie?query=%%&primary_release_year=##&api_key=" + api_key, movie, releaseYear);
        String response3 = apiReader.getDataFromClientWithID("https://api.themoviedb.org/3/find/%%?external_source=imdb_id&api_key=" + api_key, imbdID);
        String response4 = apiReader.getDataFromClientWithRating("https://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&vote_average.gte=%%&vote_average.lte=##&api_key=" + api_key, lowerRating, upperRating);
        String response5 = apiReader.getDataFromClientSortByYears("https://api.themoviedb.org/3/discover/movie?sort_by=primary_release_date.desc&api_key="+api_key);

        //System.out.println(response1);
        //System.out.println(response2);
        //System.out.println(response5);


        List<MovieDTO> movieDTO = apiReader.getMovieDataBySearch(response1);
        //  movieDTO.stream().forEach(System.out::println);

        List<MovieDTO> movieDTO2 = apiReader.getMovieDataBySearch(response2);
        //movieDTO2.stream().forEach(System.out::println);

        MovieDTO movieDTO3 = apiReader.getMovieDataFindByID(response3);
//        System.out.println(movieDTO3);

        List<MovieDTO> movieDTO4 = apiReader.getMovieDataBySearch(response4);
//        movieDTO4.stream().forEach(System.out::println);

        System.out.println(movieDTO3.getReleaseYear(movieDTO3.getReleaseDate()));

        List<MovieDTO> movieDTO5 = apiReader.getMovieDataBySearch(response5);
        movieDTO5.stream().forEach(System.out::println);

    }

}
