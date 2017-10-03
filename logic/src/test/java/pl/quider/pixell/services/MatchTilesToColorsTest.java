package pl.quider.pixell.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.quider.pixell.eventArgs.ImageResizedEventArgs;
import pl.quider.pixell.model.MainPicture;
import pl.quider.pixell.model.Point;
import pl.quider.pixell.model.TilePicture;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(MockitoJUnitRunner.class)
public class MatchTilesToColorsTest {
    private List<File> call;
    private List<TilePicture> tiles = new ArrayList<>();
    private MainPicture mainPicture;

    @Before
    public void setUp() throws Exception {
        CatalogSniffer catalogSniffer = new CatalogSniffer(new File("C:\\Users\\adria\\Pictures\\Camera Roll"));
        call = catalogSniffer.call();
        final ExecutorService executorService = Executors.newFixedThreadPool(11);
        call.stream().parallel().forEach(this::foreachElement);
        mainPicture = new PrepareMainPicture(new MainPicture("C:/Users/adria/Pictures/Camera Roll/20170708_163020.jpg")).call();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void call() throws Exception {
        Future<List<TilePicture>> listTiles = null;
        HashMap<Point, List<TilePicture>> map = new HashMap<>();
        for (Point point : mainPicture.getColorMap().keySet()) {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            listTiles = executorService.submit(new MatchTilesToColors(mainPicture.getColorMap().get(point), tiles));
            map.put(point, listTiles.get());
        }
        map.keySet().stream().forEach((Point point) -> {
            System.out.println(map.get(point).size());
        });
    }

    private void foreachElement(File file) {
        ImageResizer imageResizer = new ImageResizer(file, new File("tiles/"));
        imageResizer.addOnImageResizedListener(this::eventConsumer);
    }

    private void eventConsumer(ImageResizedEventArgs imageResizedEventArgs) {
        tiles.add(imageResizedEventArgs.getCreatedTile());
    }

}