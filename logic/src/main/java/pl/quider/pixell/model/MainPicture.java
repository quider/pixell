package pl.quider.pixell.model;

import pl.quider.pixell.eventArgs.ColorAddedToMapEventArgs;
import pl.quider.pixell.eventArgs.PointsSetEventArgs;
import pl.quider.pixell.eventArgs.TileAddedToMapEventArgs;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class MainPicture extends Picture {

    Map<Point, TilePicture> pictureMap;
    Map<Point, Color> colorMap;
    private File workImageFile;
    private Set<Point> points;
    private List<Consumer<ColorAddedToMapEventArgs>> addedColorToMap = new ArrayList<>();
    private List<Consumer<TileAddedToMapEventArgs>> tilesAddedToMapListeners = new ArrayList<>();
    private List<Consumer<PointsSetEventArgs>> pointSetsListnersList = new ArrayList<>();

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
        this.tilesAddedToMapListeners.forEach(this::tilesAddedToMap);
    }

    private void tilesAddedToMap(Consumer<TileAddedToMapEventArgs> tileAddedToMapEventArgsConsumer) {

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

    private void pointsSet(Consumer<PointsSetEventArgs> pointsSetEventArgsConsumer) {

    }

    public void setPoints(Set<Point> points) {
        this.points = points;
        this.pointSetsListnersList.forEach(this::pointsSet);
    }

    public void addColorToMap(Point point, Color color) {
        this.colorMap.put(point, color);
        this.addedColorToMap.forEach(this::addedColorToMap);
    }

    private void addedColorToMap(Consumer<ColorAddedToMapEventArgs> colorAddedToMapEventArgsConsumer) {

    }

    public Map<Point, Color> getColorMap() {
        return colorMap;
    }
}
