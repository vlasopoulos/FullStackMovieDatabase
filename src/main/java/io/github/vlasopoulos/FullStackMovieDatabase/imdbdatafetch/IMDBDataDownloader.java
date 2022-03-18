package io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class IMDBDataDownloader extends Thread {

    URL domain;
    private final Map<URL,File> urlFileMap;

    @Autowired
    private UpdateDatabaseTables updateDatabaseTables;


    public IMDBDataDownloader()  throws IOException {
        domain = new URL("https://datasets.imdbws.com");
        Files.createDirectories(Paths.get("temp"));
        urlFileMap = Map.of(
                new URL(domain + "/title.episode.tsv.gz"), new File("temp/title.episode.tsv.gz")
                ,new URL(domain + "/name.basics.tsv.gz"), new File("temp/name.basics.tsv.gz")
                ,new URL(domain + "/title.akas.tsv.gz"), new File("temp/title.akas.tsv.gz")
                ,new URL(domain + "/title.basics.tsv.gz"), new File("temp/title.basics.tsv.gz")
                ,new URL(domain + "/title.crew.tsv.gz"), new File("temp/title.crew.tsv.gz")
                ,new URL(domain + "/title.principals.tsv.gz"), new File("temp/title.principals.tsv.gz")
                ,new URL(domain + "/title.ratings.tsv.gz"), new File("temp/title.ratings.tsv.gz")
        );
    }




    private boolean download() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(7);
        for (URL url : urlFileMap.keySet()) {
            System.out.println("Downloading " + url.toString());
            pool.submit(new DownloadTask(url, urlFileMap.get(url)));
        }
        pool.shutdown();
        return pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        try {
            System.out.println("Finished all downloads = " + download());
            new DataPreProcessor().process();
            updateDatabaseTables.update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static class DownloadTask implements Runnable {
        private final URL url;
        private final File file;

        DownloadTask(URL url, File file){
            this.url = url;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileChannel.transferFrom(readableByteChannel,0,Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Download from " + url + " finished.");
            try {
                new GZextractor(file).extract();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
