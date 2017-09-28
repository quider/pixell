package pl.quider.pixell.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Picture implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3650932533372831198L;
	private Graphics2D g2d;
	private BufferedImage bi;
	private String path ;

	/**
	 * 
	 * @param picturePath 
	 * @throws IOException
	 */
	public Picture(String picturePath) throws IOException {
		this.path = picturePath;
		bi = ImageIO.read(new File(picturePath));
	}
	
	/**
	 * Returns average color of picture
	 * @return
	 */
	public Color getAverageColor(BufferedImage bi){
		int index = 0;
		long redSum = 0;
		long greenSum = 0;
		long blueSum = 0;
		//todo: Add parameter here:
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

	public Graphics2D getG2d() {
		return g2d;
	}
	
	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
