package io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataPreProcessor {

    File titleBasics, titleBasicsOutput;
    File nameBasics, nameBasicsOutput;
    File titleCrew, titleCrewOutput;
    File titlePrincipals, titlePrincipalsOutput;
    File titleEpisode, titleEpisodeOutput;
    File titleAkas, titleAkasOutput;
    File titleRatings, titleRatingsOutput;

    public DataPreProcessor() {
        titleBasics = new File("temp/title.basics.tsv");
        titleBasicsOutput = new File("temp/title.basics.output.tsv");
        nameBasics = new File("temp/name.basics.tsv");
        nameBasicsOutput = new File("temp/name.basics.output.tsv");
        titleCrew = new File("temp/title.crew.tsv");
        titleCrewOutput = new File("temp/title.crew.output.tsv");
        titlePrincipals = new File("temp/title.principals.tsv");
        titlePrincipalsOutput = new File("temp/title.principals.output.tsv");
        titleEpisode = new File("temp/title.episode.tsv");
        titleEpisodeOutput = new File("temp/title.episode.output.tsv");
        titleAkas = new File("temp/title.akas.tsv");
        titleAkasOutput = new File("temp/title.akas.output.tsv");
        titleRatings = new File("temp/title.ratings.tsv");
        titleRatingsOutput = new File("temp/title.ratings.output.tsv");
    }

    public void process() {
        processTitleBasics();
        processNameBasics();
        processTitlePrincipals();
        processTitleCrew();
        processTitleEpisode();
        processTitleAkas();
        processTitleRatings();
    }

    private void processTitleBasics() {
        System.out.println("Processing file: " + titleBasics.getPath());
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(titleBasics), StandardCharsets.UTF_8));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(titleBasicsOutput), StandardCharsets.UTF_8));
            bw.write(br.readLine()+'\n'); // write the header
            String line;

            while ( (line = br.readLine()) != null) {
                line = (line.substring(0, line.lastIndexOf("\t") + 1) + '{' +
                        line.substring(line.lastIndexOf("\t") + 1) + "}\n")
                        .replace("\"","");
                bw.write(line);
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file: " + titleBasics.getPath());

        System.gc(); // force garbage collection for delete() to work

        System.out.println("Deleting: " + titleBasics.getPath());
        if (titleBasics.delete()) System.out.println("Deleted: " + titleBasics.getPath());
        else System.out.println("Could not delete: " + titleBasics.getPath());
    }

    private void processNameBasics() {
        System.out.println("Processing file: " + nameBasics.getPath());
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nameBasics), StandardCharsets.UTF_8));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameBasicsOutput), StandardCharsets.UTF_8));
            bw.write(br.readLine()+'\n'); // write the header
            String line;

            while ( (line = br.readLine()) != null) {
                int secondToLastIndexOfTab = line.lastIndexOf("\t", line.lastIndexOf("\t") - 1) + 1;
                line = line.substring(0, secondToLastIndexOfTab) + '{'
                        + line.substring(secondToLastIndexOfTab,line.lastIndexOf("\t")) + "}\t{"
                        + line.substring(line.lastIndexOf("\t")+1) + "}\n";
                bw.write(line);
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file: " + nameBasics.getPath());

        System.gc(); // force garbage collection for delete() to work

        System.out.println("Deleting: " + nameBasics.getPath());
        if (nameBasics.delete()) System.out.println("Deleted: " + nameBasics.getPath());
        else System.out.println("Could not delete: " + nameBasics.getPath());
    }

    private void processTitlePrincipals() {
        System.out.println("Processing file: " + titlePrincipals.getPath());
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(titlePrincipals), StandardCharsets.UTF_8));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(titlePrincipalsOutput), StandardCharsets.UTF_8));
            bw.write(br.readLine()+'\n'); // write the header
            String line;

            while ( (line = br.readLine()) != null) {
                line = (line.substring(0, line.lastIndexOf("\t") + 1).replace("{","") + '{' +
                        line.substring(line.lastIndexOf("\t") + 1).replace("{","") + "}\n")
                        .replace("\"","")
                        .replace("[","")
                        .replace("]","")
                        .replace("\\}","}")
                        .replace(",}","}")
                        .replace("}}","}")
                        .replace(",,","")
                        .replace("\\}","}")
                        .replace(", }","}")
                        .replace(",}", "}");
                bw.write(line);
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file: " + titlePrincipals.getPath());

        System.gc(); // force garbage collection for delete() to work

        System.out.println("Deleting: " + titlePrincipals.getPath());
        if (titlePrincipals.delete()) System.out.println("Deleted: " + titlePrincipals.getPath());
        else System.out.println("Could not delete: " + titlePrincipals.getPath());
    }

    private void processTitleCrew() {
        System.out.println("Processing file: " + titleCrew.getPath());
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(titleCrew), StandardCharsets.UTF_8));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(titleCrewOutput), StandardCharsets.UTF_8));
            bw.write(br.readLine()+'\n'); // write the header
            String line;

            while ( (line = br.readLine()) != null) {
                int secondToLastIndexOfTab = line.lastIndexOf("\t", line.lastIndexOf("\t") - 1) + 1;
                line = line.substring(0, secondToLastIndexOfTab) + '{'
                        + line.substring(secondToLastIndexOfTab,line.lastIndexOf("\t")) + "}\t{"
                        + line.substring(line.lastIndexOf("\t")+1) + "}\n";
                bw.write(line);
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file: " + titleCrew.getPath());

        System.gc(); // force garbage collection for delete() to work

        System.out.println("Deleting: " + titleCrew.getPath());
        if (titleCrew.delete()) System.out.println("Deleted: " + titleCrew.getPath());
        else System.out.println("Could not delete: " + titleCrew.getPath());
    }

    private void processTitleEpisode() {
        System.out.println("Processing file: " + titleEpisode.getPath());
        try {
            Files.move(titleEpisode.toPath(),titleEpisodeOutput.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file: " + titleEpisode.getPath());
    }

    private void processTitleAkas() {
        System.out.println("Processing file: " + titleAkas.getPath());
        try {
            Files.move(titleAkas.toPath(),titleAkasOutput.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file: " + titleAkas.getPath());
    }

    private void processTitleRatings() {
        System.out.println("Processing file: " + titleRatings.getPath());
        try {
            Files.move(titleRatings.toPath(),titleRatingsOutput.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing file: " + titleRatings.getPath());
    }
}
