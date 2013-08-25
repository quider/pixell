/**
 * 
 */
package pl.quider.pixell;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import pl.quider.pixell.events.ImageInserted;

/**
 * @author Adrian
 *
 */
public class ReplaceColorWithImage implements Runnable {

	private Point point;
	private BufferedImage bi;
	private BufferedImage small;
	private ArrayList<ImageInserted> listeners;
	
	/**
	 * 
	 * @param p
	 * @param big
	 * @param small
	 */
	public ReplaceColorWithImage(Point p, BufferedImage big, BufferedImage small) {
		listeners = new ArrayList<ImageInserted>();
		this.small = small;
		this.bi = big;
		this.point = p;
	}
	
	/**
	 * 
	 * @param l
	 */
	public void addListener(ImageInserted l){
		listeners.add(l);
	}
	
	/**
	 * 
	 * @param l
	 */
	public void removeListener(ImageInserted l){
		listeners.remove(l);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Image image = small.getScaledInstance(point.getW(), point.getH(), BufferedImage.SCALE_SMOOTH);
		Graphics2D graphics = (Graphics2D) bi.getGraphics();
		graphics.drawImage(image, point.getX(), point.getY(), point.getW(), point.getH(), null);
//		Graphics2D graphics2 = (Graphics2D) small.getGraphics();
//		graphics2.drawImage(image, point.getX(), point.getY(), point.getW(), point.getH(), null);
//		graphics2.setColor(Color.RED);
//		graphics2.fillRect(0, 0, 20, 20);
		if(!listeners.isEmpty()){
			for (ImageInserted i : listeners) {
				i.onImageInsertion();
			}
		}
	}

}
