package pl.quider.pixell.events;

import pl.quider.pixell.eventArgs.TileImageInsertedToMainImageEventArgs;

import java.util.function.Consumer;

public interface OnTileImageInsertedToMainImageEvent {
    void addOnTileImageInsertedToMainImageListener(Consumer<TileImageInsertedToMainImageEventArgs> listener);

    void removeOnTileImageInsertedToMainImageListener(Consumer<TileImageInsertedToMainImageEventArgs> listener);
}
