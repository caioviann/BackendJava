package viana.nunes.henrique.caio.screenAz.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episode {
    private Integer season;
    private String title;
    private Integer numEpisodio;
    private Double rating;
    private LocalDate releaseDate;

    public Episode(Integer numberSeason, EpisodeData episodeData) {
        this.season = numberSeason;
        this.title = episodeData.title();
        this.numEpisodio = episodeData.num();

        try {
            this.rating = Double.valueOf(episodeData.rating());
        }catch (NumberFormatException e){
            this.rating = 0.0;
        }

        try{
            this.releaseDate = LocalDate.parse(episodeData.releaseDate());
        }catch (DateTimeException e){
            this.releaseDate = null;
        }


    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumEpisodio() {
        return numEpisodio;
    }

    public void setNumEpisodio(Integer numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", numEpisodio=" + numEpisodio +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate;
    }
}
