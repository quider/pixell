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
