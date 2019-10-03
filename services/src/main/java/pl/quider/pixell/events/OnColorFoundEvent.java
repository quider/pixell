package pl.quider.pixell.events;

import pl.quider.pixell.eventArgs.ColorFoundEventArgs;

import java.util.function.Consumer;

public interface OnColorFoundEvent {

    void addColorFoundListener(Consumer<ColorFoundEventArgs> consumer);
    void removeColorFoundListener(Consumer<ColorFoundEventArgs> consumer);

}
