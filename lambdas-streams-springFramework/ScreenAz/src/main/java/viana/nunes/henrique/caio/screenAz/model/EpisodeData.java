package viana.nunes.henrique.caio.screenAz.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer num,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Released") String releaseDate) {
}
