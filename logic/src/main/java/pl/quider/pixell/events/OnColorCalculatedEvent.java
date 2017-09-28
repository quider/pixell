package pl.quider.pixell.events;

import pl.quider.pixell.eventArgs.ColorCalculatedEventArgs;

import java.util.function.Consumer;

public interface OnColorCalculatedEvent {

    void addOnColorCalculatedListener(Consumer<ColorCalculatedEventArgs> listener);

    void removeOnColorCalculatedListener(Consumer<ColorCalculatedEventArgs> listener);
}
