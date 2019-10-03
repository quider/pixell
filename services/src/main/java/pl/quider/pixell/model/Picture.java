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
	private String path ;

	/**
	 * 
	 * @param picturePath 
	 * @throws IOException
	 */
	public Picture(String picturePath) throws IOException {
		this.path = picturePath;
	}
	

	public Graphics2D getG2d() {
		return g2d;
	}
	
	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

    /**
     * This getter creates new object of {@link BufferedImage} by
     * {@link ImageIO#read(File)}. It does not keep instance of
     * {@link BufferedImage} as a field.
     *
     * @return
     * @throws IOException
     */
    public BufferedImage getBi() throws IOException {
        return ImageIO.read(new File(this.path));
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
