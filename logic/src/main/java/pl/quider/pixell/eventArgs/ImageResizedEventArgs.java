package pl.quider.pixell.eventArgs;

import pl.quider.pixell.model.TilePicture;

import java.io.File;

public class ImageResizedEventArgs extends EventArgs {
    private File originalFile;
    private TilePicture createdTile;

    public ImageResizedEventArgs(Object sender, File originalFile, TilePicture createdTile) {
        super(sender);
        this.originalFile = originalFile;
        this.createdTile = createdTile;
    }

    public File getOriginalFile() {
        return originalFile;
    }

    public TilePicture getCreatedTile() {
        return createdTile;
    }
}
