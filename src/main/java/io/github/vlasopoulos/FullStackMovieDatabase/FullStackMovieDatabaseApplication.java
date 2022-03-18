package io.github.vlasopoulos.FullStackMovieDatabase;

import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.DataPreProcessor;
import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.GZextractor;
import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.IMDBDataDownloader;
import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.UpdateDatabaseTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class FullStackMovieDatabaseApplication {

	@Autowired
	IMDBDataDownloader imdbDataDownloader;

	public static void main(String[] args) {
		SpringApplication.run(FullStackMovieDatabaseApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException, InterruptedException {
//		System.out.println("Starting imdbDataDownloader");
//		Thread imdbDataDownloaderThread = imdbDataDownloader;
//		imdbDataDownloaderThread.start();
//		System.out.println("Started imdbDataDownloaderThread");
	}
}
