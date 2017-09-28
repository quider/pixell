package pl.quider.pixell.eventArgs;

import java.io.File;

public class ImageFileFoundEventArgs {
    private final File file;


    public ImageFileFoundEventArgs(File element) {
        this.file = element;
    }

    public File getFile() {
        return file;
    }
}
