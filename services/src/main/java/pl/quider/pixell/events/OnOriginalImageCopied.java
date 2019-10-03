package pl.quider.pixell.events;


import pl.quider.pixell.eventArgs.OriginalImageCopiedEventArgs;

import java.util.function.Consumer;

public interface OnOriginalImageCopied {
    void addOnOriginalImageCopiedListener(Consumer<OriginalImageCopiedEventArgs> listener);

    void removeOnOriginalImageCopiedistener(Consumer<OriginalImageCopiedEventArgs> listener);
}
