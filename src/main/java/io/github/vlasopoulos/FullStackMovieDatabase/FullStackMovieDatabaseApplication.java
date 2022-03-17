package io.github.vlasopoulos.FullStackMovieDatabase;

import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.DataPreProcessor;
import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.GZextractor;
import io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch.IMDBDataDownloader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class FullStackMovieDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackMovieDatabaseApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException, InterruptedException {
		System.out.println("Starting imdbDataDownloader");
		Thread imdbDataDownloaderThread = new IMDBDataDownloader();
		imdbDataDownloaderThread.start();
		System.out.println("Started imdbDataDownloaderThread");

//		DataPreProcessor dataPreProcessor = new DataPreProcessor();
//		dataPreProcessor.process();

	}
}
