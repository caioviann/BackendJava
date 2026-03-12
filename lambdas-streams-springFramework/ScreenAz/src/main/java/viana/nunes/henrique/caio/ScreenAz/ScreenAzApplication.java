package viana.nunes.henrique.caio.ScreenAz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenAzApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ScreenAzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("First project spring without web");
	}
}
