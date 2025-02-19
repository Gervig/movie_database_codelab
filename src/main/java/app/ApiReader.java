package app;

import app.DTOs.MovieDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ApiReader
{
    private static ObjectMapper objectMapper = new ObjectMapper();


    public String getDataFromClientWithID(String url, String id)
    {
        String movieURL = url.replace("%%", id);

        try
        {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(movieURL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                String body = response.body();
                return body;
            } else
            {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getDataFromClientWithTitle(String url, String title)
    {
        String movieURL = url.replace("%%", title);
        movieURL = movieURL.replace(" ", "%20");

        try
        {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(movieURL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                String body = response.body();
                return body;
            } else
            {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public String getDataFromClientWithTitleAndYear(String url, String title, String releaseYear)
    {
        String movieURL = url.replace("%%", title);
        movieURL = movieURL.replace(" ", "%20");
        movieURL = movieURL.replace("##", releaseYear);

        try
        {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(movieURL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                String body = response.body();
                return body;
            } else
            {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getDataFromClientWithRating(String url, Float lowerRating, Float upperRating)
    {
        String lowRating = String.valueOf(lowerRating);
        String upRating = String.valueOf(upperRating);
        String movieURL = url.replace("%%", lowRating);
        movieURL = movieURL.replace("##", upRating);

        try
        {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(movieURL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
            {
                String body = response.body();
                return body;
            } else
            {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }




    public List<MovieDTO> getMovieDataBySearch(String json)
    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        try
        {
            // Map the 'results' array from the JSON response to a List of MovieDTOs
            return Arrays.asList(objectMapper.readValue(
                    objectMapper.readTree(json).get("results").toString(), MovieDTO[].class
            ));

        } catch (JsonProcessingException jPE)
        {
            jPE.printStackTrace();
        }
        return null;
    }


    public MovieDTO getMovieDataFindByID(String json)
    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        try
        {
            MovieDTO movieDTO = objectMapper.readValue(
                    objectMapper.readTree(json).get("movie_results").toString(),MovieDTO[].class)[0];

            return movieDTO;
        } catch (JsonProcessingException jPE)
        {
            jPE.printStackTrace();
        }
        return null;
    }



}
