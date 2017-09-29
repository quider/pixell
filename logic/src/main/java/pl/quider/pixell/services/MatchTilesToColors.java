package pl.quider.pixell.services;

import pl.quider.pixell.eventArgs.ImageMatchToColorEventArgs;
import pl.quider.pixell.events.OnImageMatchToColorEvent;
import pl.quider.pixell.model.TilePicture;

import java.awt.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MatchTilesToColors implements Callable<List<TilePicture>>, OnImageMatchToColorEvent {

    private Color color;
    private List<Consumer<ImageMatchToColorEventArgs>> imageMatchToColorListeners;

    public MatchTilesToColors(Color color) {
        this.color = color;
    }

    @Override
    public List<TilePicture> call() throws Exception {
        return null;
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
