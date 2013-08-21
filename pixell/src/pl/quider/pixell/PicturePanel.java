package pl.quider.pixell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PicturePanel extends JPanel {

	private BufferedImage image;
	
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 647855451026949037L;

	public void loadPicture(BufferedImage image){
		this.image = image;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		if(image != null){
			Graphics2D gd = (Graphics2D) g;
			gd.drawImage(image,0,0, 640, 480,null);
		}
	}
}
