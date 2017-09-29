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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class ImageResizer implements Runnable, OnImageResizedEvent {
    private final List<Consumer<ImageResizedEventArgs>> imageResizedListeners = new ArrayList<>();
    private final File originalImageFile;
    private final File newDestination;
    private double lenFactor = new Double(Register.getInstance().getProperty(SettingsUtils.IMAGE_FACTOR, "0.015"));


    public ImageResizer(File originalImageFile, File newDestination) {
        this.originalImageFile = originalImageFile;
        this.newDestination = newDestination;
    }

    @Override
    public void run() {
        try {
            BufferedImage read = ImageIO.read(originalImageFile);
            int h = read.getHeight();
            int w = read.getWidth();
            int wMini; // szerokosc miniaturowego zdjecia
            int hMini;// wysokosc miniturowego zdjecia
            if (isSquare()) {
                wMini = (int) (w * lenFactor);
                hMini = h / (h / wMini);
            } else {
                wMini = (int) (w * lenFactor); // szerokosc miniaturowego zdjecia
                hMini = (int) (h * lenFactor);// wysokosc miniturowego zdjecia
            }
            BufferedImage resizedImage = new BufferedImage(wMini, hMini, read.getType());
            Graphics2D g = resizedImage.createGraphics();
            boolean b = g.drawImage(read, 0, 0, wMini, hMini, null);
            g.dispose();
            TilePicture tilePicture = null;
            File newDestinationFile = getNewDestinationFile(originalImageFile.getName());
            ImageIO.write(resizedImage, "jpg", newDestinationFile);
            tilePicture = new TilePicture(newDestinationFile.getAbsolutePath());
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<Color> submit = executorService.submit(new AverageColorCalculator(tilePicture));
            tilePicture.setColor(submit.get());
            ImageResizedEventArgs imageResizedEventArgs = new ImageResizedEventArgs(this, this.originalImageFile, tilePicture);
            imageResizedListeners.forEach(listener -> listener.accept(imageResizedEventArgs));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private boolean isSquare() {
        return Boolean.parseBoolean(Register.getInstance().getProperty("tile.image.square", "false"));
    }

    private File getNewDestinationFile(String name) {
        this.newDestination.mkdirs();
        final File tile = new File(this.newDestination, name);
        return tile;
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
