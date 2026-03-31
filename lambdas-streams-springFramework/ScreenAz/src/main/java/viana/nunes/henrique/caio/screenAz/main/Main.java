package viana.nunes.henrique.caio.screenAz.main;

import viana.nunes.henrique.caio.screenAz.service.ApiConsumption;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private ApiConsumption apiConsumption = new ApiConsumption();

    private final String ADDRESS = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=ce3ed0d3";

    public void displayMenu(){
        System.out.print("Digite o nome da serie para busca: ");
        var serieName = scanner.nextLine();
        var json = apiConsumption.getDatas(ADDRESS + serieName.replace(" ", "+") + API_KEY);
    }
}
