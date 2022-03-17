package io.github.vlasopoulos.FullStackMovieDatabase.imdbdatafetch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class GZextractor {
    File file;
    File extractedFile;

    public GZextractor(File file) {
        this.file = file;
        extractedFile = new File(file.getPath().replace(".gz",""));
    }

    public void extract() throws IOException {
        System.out.println("Extracting: " + file.getPath());
        FileInputStream fis = new FileInputStream(file);
        GZIPInputStream gis = new GZIPInputStream(fis);
        InputStreamReader isr = new InputStreamReader(gis,StandardCharsets.UTF_8);
        FileOutputStream fos = new FileOutputStream(extractedFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos,StandardCharsets.UTF_8);

        char[] buffer = new char[1024];
        int len;
        while ((len = isr.read(buffer)) > 0) {
            osw.write(buffer, 0, len);
        }

        osw.close();
        fos.close();
        isr.close();
        gis.close();
        fis.close();

        System.gc(); // force garbage collection for delete() to work

        System.out.println("Finished extracting: " + extractedFile.getPath() );
        System.out.println("Deleting: " + file.getPath());
        if (file.delete()) System.out.println("Deleted: " + file.getPath());
        else System.out.println("Could not delete: " + file.getPath());
    }
}
