package app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiReader
{
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
            } else {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
