package pl.quider.pixell.services;

import pl.quider.pixell.Register;
import pl.quider.pixell.eventArgs.ImageResizedEventArgs;
import pl.quider.pixell.events.OnImageResizedEvent;
import pl.quider.pixell.model.TilePicture;
import pl.quider.pixell.settings.SettingsUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ImageResizer implements Callable<TilePicture>, OnImageResizedEvent {
    private final List<Consumer<ImageResizedEventArgs>> imageResizedListeners = new ArrayList<>();
    private final File originalImageFile;
    private final File newDestination;
    private double lenFactor = new Double(Register.getInstance().getProperty(SettingsUtils.IMAGE_FACTOR, "0.015"));


    public ImageResizer(File originalImageFile, File newDestination) {
        this.originalImageFile = originalImageFile;
        this.newDestination = newDestination;
    }

    @Override
    public TilePicture call() throws Exception {
        Integer biggerSide = Integer.valueOf(Register.getInstance().getProperty("tile.image.bigger.side", "20"));
        BufferedImage read = ImageIO.read(originalImageFile);
        int h = read.getHeight();
        int w = read.getWidth();
        int wMini = (int) (w * lenFactor); // szerokosc miniaturowego zdjecia
        int hMini = h / (h / wMini);// wysokosc miniturowego zdjecia

        BufferedImage resizedImage = new BufferedImage(wMini, hMini, read.getType());
        Graphics2D g = resizedImage.createGraphics();
        boolean b = g.drawImage(read, 0, 0, wMini, hMini, null);
        g.dispose();
        ImageIO.write(resizedImage, "jpg", getNewDestinationFile());
        return null;
    }

    private File getNewDestinationFile() {
        //todo: combine paths
        return null;
    }

    @Override
    public void addOnImageResizedListener(Consumer<ImageResizedEventArgs> listener) {
        this.imageResizedListeners.add(listener);
    }

    @Override
    public void removeOnImageResizedListener(Consumer<ImageResizedEventArgs> listener) {
        this.imageResizedListeners.remove(listener);
    }
}
