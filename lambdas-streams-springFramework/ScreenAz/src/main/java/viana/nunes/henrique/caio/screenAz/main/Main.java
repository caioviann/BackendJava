package viana.nunes.henrique.caio.screenAz.main;

import viana.nunes.henrique.caio.screenAz.model.SeasonData;
import viana.nunes.henrique.caio.screenAz.model.SerieData;
import viana.nunes.henrique.caio.screenAz.service.ApiConsumption;
import viana.nunes.henrique.caio.screenAz.service.ConvertsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private ApiConsumption apiConsumption = new ApiConsumption();

    private ConvertsData converter = new ConvertsData();

    private final String ADDRESS = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=ce3ed0d3";

    public void displayMenu(){
        System.out.print("Digite o nome da serie para busca: ");
        var serieName = scanner.nextLine();
        var json = apiConsumption.getDatas(ADDRESS + serieName.replace(" ", "+") + API_KEY);

        SerieData data = converter.getData(json, SerieData.class);
        System.out.println(data);

        List<SeasonData> seasons = new ArrayList<>();

		for(int i = 1; i <= data.totalSeasons(); i++){
            json = apiConsumption.getDatas(ADDRESS + serieName.replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = converter.getData(json, SeasonData.class);
			seasons.add(seasonData);
		}
		seasons.forEach(System.out::println);
    }
}
