package pl.quider.pixell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.SwingWorker;

import pl.quider.pixel.events.ImagePaintedListener;

public class MainPicture extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3498357329889565399L;
	private Graphics2D g2d;
	private BufferedImage bi;
	private List<ImagePaintedListener> listeners;
	private double lenFactor = 0.05; 

	public MainPicture(String picturePath) throws IOException {
		listeners = new ArrayList<ImagePaintedListener>();
		if (picturePath != null && !picturePath.isEmpty()){
			readInPicture(picturePath);
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
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

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	/**
	 * Wczytuje obraz
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public MainPicture readInPicture(String path) throws IOException {
		bi = ImageIO.read(new File(path));
		new ImageCountWorker(bi).execute();
		return this;
	}

	/**
	 * Dodaje listenera
	 * 
	 * @param l
	 * @return
	 */
	public MainPicture addListener(ImagePaintedListener l) {
		listeners.add(l);
		return this;
	}

	/**
	 * Usuwa listenera
	 * 
	 * @param l
	 * @return
	 */
	public MainPicture removeListener(ImagePaintedListener l) {
		listeners.remove(l);
		return this;
	}

	class ImageCountWorker extends SwingWorker<Boolean, String> {
		
		private BufferedImage bi;

		public ImageCountWorker(BufferedImage bi) {
			this.bi = bi;
		}
		
		@Override
		protected void done() {
	
		}

		@Override
		protected Boolean doInBackground() throws Exception {
			int h = bi.getHeight();
			int w = bi.getWidth();
			double div = h/lenFactor;
			
			
			
			return null;
		}

	}
}
