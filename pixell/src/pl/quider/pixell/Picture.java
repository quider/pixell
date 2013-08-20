package pl.quider.pixell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.xml.ws.WebServiceException;

import com.sun.xml.internal.ws.Closeable;

public class Picture extends JComponent implements Closeable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3650932533372831198L;
	private Graphics2D g2d;
	private BufferedImage bi;

	/**
	 * 
	 * @param pictureDirectory katalog ze zdjêciami do mozajki
	 * @throws IOException
	 */
	public Picture(String pictureDirectory) throws IOException {
		System.out.println("Picture:");
		bi = ImageIO.read(new File(pictureDirectory));
	}
	
	/**
	 * Returns average color of picture
	 * @return
	 */
	public Color getAverageColor(){
		int index = 0;
		long redSum = 0;
		long greenSum = 0;
		long blueSum = 0;
		for (int x = bi.getMinX(); x < bi.getWidth(); x+=4) {
			for(int y=bi.getMinY(); y < bi.getHeight(); y+=4){
				int rgb = bi.getRGB(x, y);
				Color c = new Color(rgb);
				int red =  c.getRed();
				redSum += red;
				int green = c.getGreen();
				greenSum += green;
				int blue = c.getBlue();
				blueSum += blue;
				c = new Color(red, green, blue);
				bi.setRGB(x, y, c.getRGB());
				index++;
			}
		}
		return new Color(new Integer((int) (redSum/index)), (int)greenSum/index, (int)blueSum/index);
	}

	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		g2d.setColor(Color.WHITE);
		g2d.setColor(getAverageColor());
		g2d.fillRect(bi.getMinX(), bi.getMinY(), 60, 60);
	}


	@Override
	public void close() throws WebServiceException {
		this.bi = null;
		this.g2d = null;
		Runtime.getRuntime().gc();
	}

	public Graphics2D getG2d() {
		return g2d;
	}
	
	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}
}
