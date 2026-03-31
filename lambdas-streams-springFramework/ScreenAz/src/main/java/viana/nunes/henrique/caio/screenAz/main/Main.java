package viana.nunes.henrique.caio.screenAz.main;

import viana.nunes.henrique.caio.screenAz.model.Episode;
import viana.nunes.henrique.caio.screenAz.model.EpisodeData;
import viana.nunes.henrique.caio.screenAz.model.SeasonData;
import viana.nunes.henrique.caio.screenAz.model.SerieData;
import viana.nunes.henrique.caio.screenAz.service.ApiConsumption;
import viana.nunes.henrique.caio.screenAz.service.ConvertsData;

import java.util.*;
import java.util.stream.Collectors;

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

//        for (int i = 0; i < data.totalSeasons(); i++){
//            List<EpisodeData> episodeSeasons = seasons.get(i).episodes();
//            for (int j = 0; j < episodeSeasons.size(); j++) {
//                System.out.println(episodeSeasons.get(j).title());
//            }
//        }
        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

//      List<String> names = Arrays.asList("Jacque", "Iasmim", "Paulo", "Rodrigo", "Nico");
//      names.stream().sorted().limit(3).filter(n -> n.startsWith("N")).map(n -> n.toUpperCase()).forEach(System.out::println);

        List<EpisodeData> episodeData = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episodios: ");

        episodeData.stream()
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(d -> new Episode(s.number(), d))
                ).collect(Collectors.toList());
        episodes.forEach(System.out::println);
    }
}
