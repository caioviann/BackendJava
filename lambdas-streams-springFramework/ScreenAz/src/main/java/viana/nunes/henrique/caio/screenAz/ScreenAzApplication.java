package viana.nunes.henrique.caio.screenAz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import viana.nunes.henrique.caio.screenAz.model.EpisodeData;
import viana.nunes.henrique.caio.screenAz.model.SerieData;
import viana.nunes.henrique.caio.screenAz.service.ApiConsumption;
import viana.nunes.henrique.caio.screenAz.service.ConvertsData;

@SpringBootApplication
public class ScreenAzApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ScreenAzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiConsumption = new ApiConsumption();
		var json = apiConsumption.getDatas("https://www.omdbapi.com/?t=the+flash&apikey=ce3ed0d3");
		System.out.println(json);

		ConvertsData converter = new ConvertsData();
		SerieData data = converter.getData(json, SerieData.class);
		System.out.println(data);

		json = apiConsumption.getDatas("https://www.omdbapi.com/?t=the+flash&season=1&episode=1&apikey=ce3ed0d3");

		EpisodeData episodeData = converter.getData(json, EpisodeData.class);
		System.out.println("Dados episodio: " + episodeData);
	}
}
