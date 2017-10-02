package pl.quider.pixell.model;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainPicture extends Picture {

    Map<Point, TilePicture> pictureMap;
    Map<Point, Color> colorMap;
    private File workImageFile;
    private Set<Point> points;

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

    public Set<Point> getPoints() {
        return colorMap.keySet();
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
        this.colorMap = new HashMap<>();
        this.pictureMap = new HashMap<>();
    }
}
