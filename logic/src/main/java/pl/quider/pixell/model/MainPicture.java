package pl.quider.pixell.model;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainPicture extends Picture {

    Map<Point, TilePicture> pictureMap;
    Map<Point, Color> colorMap;
    private File workImageFile;

    /**
     * @param picturePath
     * @throws IOException
     */
    public MainPicture(String picturePath) throws IOException {
        super(picturePath);
        pictureMap = new HashMap<>();
        colorMap = new HashMap<>();
    }

    public void addPictureToMap(Point point, TilePicture picture) {
        pictureMap.put(point, picture);
    }

    public File getWorkImageFile() {
        return workImageFile;
    }

    public void setWorkImageFile(File workImageFile) {
        this.workImageFile = workImageFile;
    }
}
