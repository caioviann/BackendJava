package viana.nunes.henrique.caio.screenAz.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record SerieData(@JsonAlias("Title") String title,
                        @JsonAlias("totalSeasons") Integer seasons,
                        @JsonAlias("imdbRating") String rating) {
}
