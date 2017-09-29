package pl.quider.pixell.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainPicture extends Picture {

    Map<Point, TilePicture> pictureMap;

    /**
     * @param picturePath
     * @throws IOException
     */
    public MainPicture(String picturePath) throws IOException {
        super(picturePath);
        pictureMap = new HashMap<>();
    }

    public void addPictureToMap(Point point, TilePicture picture) {
        pictureMap.put(point, picture);
    }
}
