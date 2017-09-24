package pl.quider.pixell.events;

import pl.quider.pixell.eventArgs.ImageMatchToColorEventArgs;

import java.util.function.Consumer;

public interface OnImageMatchToColorEvent {
    void addMatchToColorListner(Consumer<ImageMatchToColorEventArgs> consumer);
    void removeMatchToColorListener(Consumer<ImageMatchToColorEventArgs> consumer);
}
