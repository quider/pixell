/**
 * 
 */
package pl.quider.pixell;

import pl.quider.pixell.events.ImageInserted;
import pl.quider.pixell.model.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian
 * 
 */
public class ReplaceColorWithImage implements Runnable {

	private List<Point> points;
	private BufferedImage bi;
	private BufferedImage small;
	private ArrayList<ImageInserted> listeners;

	/**
	 * 
	 * @param p
	 * @param big
	 * @param small
	 */
	public ReplaceColorWithImage(List<Point> p, BufferedImage big, BufferedImage small) {
		listeners = new ArrayList<ImageInserted>();
		this.small = small;
		this.bi = big;
		this.points = p;
	}

	/**
	 * 
	 * @param l
	 */
	public void addListener(ImageInserted l) {
		listeners.add(l);
	}

	/**
	 * 
	 * @param l
	 */
	public void removeListener(ImageInserted l) {
		listeners.remove(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (Point point : points) {

			Image image = small.getScaledInstance(point.getW(), point.getH(), BufferedImage.SCALE_SMOOTH);
			Graphics2D graphics = (Graphics2D) bi.getGraphics();
			graphics.drawImage(image, point.getX(), point.getY(), point.getW(), point.getH(), null);
			// Graphics2D graphics2 = (Graphics2D) small.getGraphics();
			// graphics2.drawImage(image, point.getX(), point.getY(),
			// point.getW(), point.getH(), null);
			// graphics2.setColor(Color.RED);
			// graphics2.fillRect(0, 0, 20, 20);
		}
		if (!listeners.isEmpty()) {
			for (ImageInserted i : listeners) {
				i.onImageInsertion();
			}
		}
	}

}
