package pl.quider.pixell.services;

import pl.quider.pixell.eventArgs.ColorCalculatedEventArgs;
import pl.quider.pixell.events.OnColorCalculatedEvent;
import pl.quider.pixell.model.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class AverageColorCalculator implements Callable<Color>, OnColorCalculatedEvent {
    private Picture picture;
    private List<Consumer<ColorCalculatedEventArgs>> colorCalculatedListener = new ArrayList<>();

    public AverageColorCalculator(Picture picture) {
        this.picture = picture;
    }

    @Override
    public Color call() throws Exception {
        if (this.picture == null) throw new NullPointerException("No picture");
        BufferedImage bi = picture.getBi();
        //todo: Add parameter here:
        Color color = getColor(bi, bi.getMinX(), bi.getMinY(), bi.getWidth(), bi.getHeight());

        for (Consumer<ColorCalculatedEventArgs> listener : this.colorCalculatedListener) {
            listener.accept(new ColorCalculatedEventArgs(this, color, this.picture));
        }
        return color;
    }

    public Color getColor(BufferedImage bi, int minX, int minY, int minWidth, int minHeight) {
        Color color = null;
        long redSum = 0;
        long greenSum = 0;
        long blueSum = 0;
        int index = 0;
        for (int x = minX; x < minWidth; x += 4) {
            for (int y = minY; y < minHeight; y += 4) {
                int rgb = bi.getRGB(x, y);
                Color c = new Color(rgb);
                redSum += c.getRed();
                greenSum += c.getGreen();
                blueSum += c.getBlue();
                index++;
            }
        }
        color = new Color(new Integer((int) (redSum / index)), (int) greenSum / index, (int) blueSum / index);
        return color;
    }

    @Override
    public void addOnColorCalculatedListener(Consumer<ColorCalculatedEventArgs> listener) {
        this.colorCalculatedListener.add(listener);
    }

    @Override
    public void removeOnColorCalculatedListener(Consumer<ColorCalculatedEventArgs> listener) {
        this.colorCalculatedListener.remove(listener);
    }
}
