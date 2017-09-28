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

    public AverageColorCalculator() {
    }

    @Override
    public Color call() throws Exception {
        if (this.picture == null) throw new NullPointerException("No picture");
        BufferedImage bi = picture.getBi();
        int index = 0;
        long redSum = 0;
        long greenSum = 0;
        long blueSum = 0;
        //todo: Add parameter here:
        Color color = null;
        for (int x = bi.getMinX(); x < bi.getWidth(); x += 4) {
            for (int y = bi.getMinY(); y < bi.getHeight(); y += 4) {
                int rgb = bi.getRGB(x, y);
                Color c = new Color(rgb);
                int red = c.getRed();
                redSum += red;
                int green = c.getGreen();
                greenSum += green;
                int blue = c.getBlue();
                blueSum += blue;
                c = new Color(red, green, blue);
                bi.setRGB(x, y, c.getRGB());
                index++;
                color = new Color(new Integer((int) (redSum / index)), (int) greenSum / index, (int) blueSum / index);

                for (Consumer<ColorCalculatedEventArgs> listener : this.colorCalculatedListener) {
                    listener.accept(new ColorCalculatedEventArgs(this, color, this.picture));
                }
            }
        }
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
