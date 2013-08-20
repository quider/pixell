package pl.quider.pixell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import pl.quider.pixel.events.ImagePaintedListener;

public class MainPicture extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3498357329889565399L;
	private Graphics2D g2d;
	private BufferedImage bi;
	private List<ImagePaintedListener> listeners;
	
	public MainPicture( String picturePath) throws IOException {
		listeners = new ArrayList<ImagePaintedListener>();
		readInPicture(picturePath);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;
//		TODO: skalowanie obrazu;
//		g2d.scale(arg0, arg1)
		g2d.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		for (ImagePaintedListener l : this.listeners) {
			l.onImagePainted(bi);
		}
	}
	
	/**
	 * Wczytuje obraz
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public MainPicture readInPicture(String path) throws IOException{
		bi = ImageIO.read(new File(path));
		return this;
	}
	
	/**
	 * Dodaje listenera
	 * @param l
	 * @return
	 */
	public MainPicture addListener(ImagePaintedListener l){
		listeners.add(l);
		return this;
	}
	
	/**
	 * Usuwa listenera
	 * @param l
	 * @return
	 */
	public MainPicture removeListener(ImagePaintedListener l ){
		listeners.remove(l);
		return this;
	}

}
