/**
 * 
 */
package pl.quider.pixell;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author Adrian
 *
 */
public class ReplaceColorWithImage implements Runnable {

	private Point point;
	private BufferedImage bi;
	private BufferedImage small;
	
	public ReplaceColorWithImage(Point p, BufferedImage big, BufferedImage small) {
		this.point = p;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Image image = small.getScaledInstance(point.getW(), point.getH(), BufferedImage.SCALE_SMOOTH);
		Graphics2D graphics = (Graphics2D) bi.getGraphics();
		graphics.drawImage(image, point.getX(), point.getY(), point.getW(), point.getH(), null);
		
		
	}

}
