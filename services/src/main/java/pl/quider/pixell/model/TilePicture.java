package pl.quider.pixell.model;

import java.awt.*;
import java.io.IOException;

public class TilePicture extends Picture {
    private Color color;

    public TilePicture(String picturePath) throws IOException {
        super(picturePath);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
