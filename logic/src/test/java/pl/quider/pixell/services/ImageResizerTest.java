package pl.quider.pixell.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageResizerTest {
    private List<File> call;

    @Before
    public void setUp() throws Exception {
        CatalogSniffer catalogSniffer = new CatalogSniffer(new File("C:\\Users\\Adrian.Kozlowski\\Pictures\\Saved Pictures"));
        call = catalogSniffer.call();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void call() throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(11);
        call.stream().parallel().forEach(file -> {
            executorService.execute(new ImageResizer(file, new File("tiles/")));
//            final TilePicture picture = new ImageResizer(file, new File("tiles/")).call();
//            System.out.println(picture.getPath());

        });

        while (!executorService.isTerminated()) ;
    }

}