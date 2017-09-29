package pl.quider.pixell.services;

import pl.quider.pixell.eventArgs.TileImageInsertedToMainImageEventArgs;
import pl.quider.pixell.events.OnTileImageInsertedToMainImageEvent;
import pl.quider.pixell.model.MainPicture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class PrepareMainPicture implements Callable<MainPicture>, OnTileImageInsertedToMainImageEvent {

    private final MainPicture mainPicture;
    List<Consumer<TileImageInsertedToMainImageEventArgs>> tileImageInsertedToMainImageListeners = new ArrayList<>();

    public PrepareMainPicture(MainPicture mainPicture) {
        this.mainPicture = mainPicture;
    }

    @Override
    public MainPicture call() throws Exception {
        return null;
    }

    @Override
    public void addOnTileImageInsertedToMainImageListener(Consumer<TileImageInsertedToMainImageEventArgs> listener) {
        this.tileImageInsertedToMainImageListeners.add(listener);
    }

    @Override
    public void removeOnTileImageInsertedToMainImageListener(Consumer<TileImageInsertedToMainImageEventArgs> listener) {
        this.tileImageInsertedToMainImageListeners.remove(listener);

    }

}
