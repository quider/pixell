package pl.quider.pixell.services;

import pl.quider.pixell.Register;
import pl.quider.pixell.eventArgs.ImageMatchToColorEventArgs;
import pl.quider.pixell.events.OnImageMatchToColorEvent;
import pl.quider.pixell.model.TilePicture;
import pl.quider.pixell.settings.SettingsConstants;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MatchTilesToColors implements Callable<List<TilePicture>>, OnImageMatchToColorEvent {

    private Color color;
    private final List<TilePicture> listOfAllPictures;
    private List<Consumer<ImageMatchToColorEventArgs>> imageMatchToColorListeners = new ArrayList<>();
    private int colorDelta = new Integer(Register.getInstance().getProperty(SettingsConstants.IMAGE_COLOR_DT, "3"));

    public MatchTilesToColors(Color color, List<TilePicture> listOfAllPictures) {
        this.color = color;
        this.listOfAllPictures = listOfAllPictures;
    }

    @Override
    public List<TilePicture> call() {
        return listOfAllPictures.stream().filter(this::filterColors).collect(Collectors.toList());
    }

    private boolean filterColors(TilePicture picture) {
        Color c = picture.getColor();
        int blue = Math.abs(c.getBlue() - this.color.getBlue());
        int red = Math.abs(c.getRed() - this.color.getRed());
        int green = Math.abs(c.getGreen() - this.color.getGreen());
        return Math.max(Math.max(blue, red), blue) < colorDelta;
    }

    @Override
    public void addMatchToColorListner(Consumer<ImageMatchToColorEventArgs> consumer) {
        this.imageMatchToColorListeners.add(consumer);
    }

    @Override
    public void removeMatchToColorListener(Consumer<ImageMatchToColorEventArgs> consumer) {
        this.imageMatchToColorListeners.remove(consumer);
    }

}
