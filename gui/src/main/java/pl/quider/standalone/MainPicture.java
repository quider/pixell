package pl.quider.standalone;

import pl.quider.pixell.Register;
import pl.quider.pixell.events.ImagePaintedListener;
import pl.quider.pixell.settings.SettingsConstants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPicture {
    /**
     *
     */
    private static final long serialVersionUID = 3498357329889565399L;
    private Graphics2D g2d;
    private BufferedImage bi;
    private HashMap<Point, Color> colorMap;
    private List<ImagePaintedListener> listeners;
    private double lenFactor;

    private int COLORDT;

    /**
     * @param picturePath
     * @throws IOException
     */
    public MainPicture(String picturePath) throws IOException {
        COLORDT = new Integer(Register.getInstance().getProperty(SettingsConstants.IMAGE_COLOR_DT, "3"));
        lenFactor = new Double(Register.getInstance().getProperty(SettingsConstants.IMAGE_FACTOR, "0.015"));
        listeners = new ArrayList<ImagePaintedListener>();

    }

    public void paint(Graphics g) {
        if (bi != null) {
            g2d = (Graphics2D) g;
            int scaledWidth = bi.getWidth();
            int scaledHeight = bi.getHeight();
            while (scaledWidth > 800) {
                scaledWidth /= 2;
                scaledHeight /= 2;
            }

            while (scaledHeight > 800) {
                scaledWidth /= 2;
                scaledHeight /= 2;
            }

            Image scaled = bi.getScaledInstance(scaledWidth, scaledHeight,
                    Image.SCALE_SMOOTH);

            g2d.drawImage(scaled, 0, 0, scaled.getWidth(null),
                    scaled.getHeight(null), null);
            for (ImagePaintedListener l : this.listeners) {
                l.onImagePainted(scaled);
            }
            scaled = null;
            Runtime.getRuntime().gc();
        }
    }
}
