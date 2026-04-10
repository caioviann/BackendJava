package viana.nunes.henrique.caio.screenAz.main;

import viana.nunes.henrique.caio.screenAz.model.Episode;
import viana.nunes.henrique.caio.screenAz.model.EpisodeData;
import viana.nunes.henrique.caio.screenAz.model.SeasonData;
import viana.nunes.henrique.caio.screenAz.model.SerieData;
import viana.nunes.henrique.caio.screenAz.service.ApiConsumption;
import viana.nunes.henrique.caio.screenAz.service.ConvertsData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

//      FORMA MAIS VERBOSA DE FAZER
//        for (int i = 0; i < data.totalSeasons(); i++){
//            List<EpisodeData> episodeSeasons = seasons.get(i).episodes();
//            for (int j = 0; j < episodeSeasons.size(); j++) {
//                System.out.println(episodeSeasons.get(j).title());
//            }
//        }

//      FORMA MAIS SIMPLES COM LAMBDAS
        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));


        List<EpisodeData> episodeData = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

//        System.out.println("\nTop 10 episodios: ");
//
//        episodeData.stream()
//                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro Filtro(N/A) " + e))
//                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite " + e))
//                .map(e -> e.title().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(d -> new Episode(s.number(), d))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);

        System.out.print("Digite o nome do episodio: ");
        var titleExcerpt = scanner.nextLine();
        Optional<Episode> episodeFound = episodes.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(titleExcerpt.toUpperCase()))
                .findFirst();
        if (episodeFound.isPresent()){
            System.out.println("Episodio encontrado:");
            System.out.println("Temporada: " + episodeFound.get().getSeason());
        }else{
            System.out.println("Episodio não encontrado!");
        }
//
//        System.out.print("A partir de que ano você deseja ver os episodios? ");
//        var year = scanner.nextInt();
//        scanner.nextLine();
//
//        LocalDate searchDate = LocalDate.of(year, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodes.stream()
//                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchDate))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getSeason() +
//                                " Episodio: " + e.getTitle() +
//                                " Data lançamento: " + e.getReleaseDate().format(formatter)
//                ));

        Map<Integer, Double> seasonsRating = episodes.stream()
                .filter(e -> e.getRating() > 0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getRating)));
        System.out.println(seasonsRating);

        DoubleSummaryStatistics est = episodes.stream()
                .filter(e -> e.getRating() > 0)
                .collect(Collectors.summarizingDouble(Episode::getRating));

        System.out.println(est);
    }
}
