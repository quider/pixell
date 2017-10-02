package pl.quider.pixell.services;

import pl.quider.pixell.Register;
import pl.quider.pixell.eventArgs.ColorCalculatedEventArgs;
import pl.quider.pixell.eventArgs.OriginalImageCopiedEventArgs;
import pl.quider.pixell.eventArgs.TileImageInsertedToMainImageEventArgs;
import pl.quider.pixell.events.OnColorCalculatedEvent;
import pl.quider.pixell.events.OnOriginalImageCopied;
import pl.quider.pixell.events.OnTileImageInsertedToMainImageEvent;
import pl.quider.pixell.model.MainPicture;
import pl.quider.pixell.model.Point;
import pl.quider.pixell.settings.SettingsConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class PrepareMainPicture implements Callable<MainPicture>,
        OnTileImageInsertedToMainImageEvent, OnOriginalImageCopied, OnColorCalculatedEvent {

    private final MainPicture mainPicture;
    private int tileWidth;
    private int tileHeight;
    private final List<Consumer<TileImageInsertedToMainImageEventArgs>> tileImageInsertedToMainImageListeners = new ArrayList<>();
    private final List<Consumer<ColorCalculatedEventArgs>> onColorCalculatedListeners = new ArrayList<>();
    private final List<Consumer<OriginalImageCopiedEventArgs>> onOriginalImageCopiedListeners = new ArrayList<>();

    public PrepareMainPicture(MainPicture mainPicture) {
        this.mainPicture = mainPicture;
    }

    @Override
    public MainPicture call() throws Exception {
        File originalMainPicture = new File(this.mainPicture.getPath());
        File parent = new File("/main/");
        parent.mkdirs();
        File workImageFile = new File(parent, originalMainPicture.getName());
        Files.copy(originalMainPicture.toPath(), new FileOutputStream(workImageFile.getAbsoluteFile()));

        this.mainPicture.setWorkImageFile(originalMainPicture);
        BufferedImage image = ImageIO.read(workImageFile);
        Double lenFactor = new Double(Register.getInstance().getProperty(SettingsConstants.IMAGE_FACTOR, "0.015"));
        this.tileWidth = (int) (image.getWidth() * lenFactor);
        this.tileHeight = (int) (image.getHeight() * lenFactor);
        int dtWidth = image.getWidth() / this.tileWidth;
        int dtHeight = image.getHeight() / this.tileHeight;
        this.setPointsInImage(dtWidth, dtHeight, image);
        return this.mainPicture;
    }

    private void setPointsInImage(int dtWidth, int dtHeight, BufferedImage image) {
        HashSet<Point> points = new HashSet<>();
        for (int x = 0; x < dtWidth; x++) {
            for (int y = 0; y < dtHeight; y++) {
                Point point = new Point(x * this.tileWidth, y * this.tileHeight, this.tileWidth, tileHeight);
                points.add(point);
                this.mainPicture.addColorToMap(point, new AverageColorCalculator(this.mainPicture).getColor(image, x * this.tileWidth, y * this.tileHeight, this.tileWidth, tileHeight));
            }
        }
        this.mainPicture.setPoints(points);
    }

    @Override
    public void addOnTileImageInsertedToMainImageListener(Consumer<TileImageInsertedToMainImageEventArgs> listener) {
        this.tileImageInsertedToMainImageListeners.add(listener);
    }

    @Override
    public void removeOnTileImageInsertedToMainImageListener(Consumer<TileImageInsertedToMainImageEventArgs> listener) {
        this.tileImageInsertedToMainImageListeners.remove(listener);

    }

    @Override
    public void addOnColorCalculatedListener(Consumer<ColorCalculatedEventArgs> listener) {
        this.onColorCalculatedListeners.add(listener);
    }

    @Override
    public void removeOnColorCalculatedListener(Consumer<ColorCalculatedEventArgs> listener) {
        this.onColorCalculatedListeners.remove(listener);
    }

    @Override
    public void addOnOriginalImageCopiedListener(Consumer<OriginalImageCopiedEventArgs> listener) {
        onOriginalImageCopiedListeners.add(listener);
    }

    @Override
    public void removeOnOriginalImageCopiedistener(Consumer<OriginalImageCopiedEventArgs> listener) {
        onOriginalImageCopiedListeners.remove(listener);
    }
}
