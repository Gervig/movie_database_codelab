package app.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDTO
{

    @JsonProperty("original_title")
    private String title;


    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("vote_average")
    private Double rating;

    @JsonProperty("adult")
    private Boolean pg13;

    @JsonProperty("overview")
    private String description;

    public String getReleaseYear(LocalDate releaseDate)
    {
        String releaseYear = String.valueOf(this.releaseDate.getYear());
        return releaseYear;
    }

}
