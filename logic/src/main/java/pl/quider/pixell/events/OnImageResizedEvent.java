package pl.quider.pixell.events;

import pl.quider.pixell.eventArgs.ImageResizedEventArgs;

import java.util.function.Consumer;

public interface OnImageResizedEvent {

    void addOnImageResizedListener(Consumer<ImageResizedEventArgs> listener);

    void removeOnImageResizedListener(Consumer<ImageResizedEventArgs> listener);
}
