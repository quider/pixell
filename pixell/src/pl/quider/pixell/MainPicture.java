package pl.quider.pixell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.SwingWorker;

import pl.quider.pixell.events.ImageInserted;
import pl.quider.pixell.events.ImagePaintedListener;

public class MainPicture extends JComponent implements ImageInserted{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3498357329889565399L;
	private Graphics2D g2d;
	private BufferedImage bi;
	private HashMap<Point,Color> colorMap;
	private List<ImagePaintedListener> listeners;
	private double lenFactor = 0.015;
	
	private final int COLORDT  =  20;
	/**
	 * 
	 * @param picturePath
	 * @throws IOException
	 */
	public MainPicture(String picturePath) throws IOException {
		listeners = new ArrayList<ImagePaintedListener>();
		if (picturePath != null && !picturePath.isEmpty()) {
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

	/**
	 * 
	 * @return
	 */
	public BufferedImage getBi() {
		return bi;
	}

	/**
	 * 
	 * @param bi
	 */
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
		colorMap = new HashMap<Point, Color>();
		new ImageCountWorker(bi).execute();
		return this;
	}

	/**
	 * Adds listenera
	 * 
	 * @param l
	 * @return
	 */
	public MainPicture addListener(ImagePaintedListener l) {
		listeners.add(l);
		return this;
	}

	/**
	 * finds color on mosaic
	 * @param c
	 */
	public List<Point> findColorOnMosaic(Color c){
		ArrayList<Point> result = new ArrayList<Point>();
		Set<Entry<Point,Color>> entrySet = colorMap.entrySet();
		Iterator<Entry<Point, Color>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<Point, Color> next = iterator.next();
			if(next.getValue().equals(c) || next.getValue().equals(c.brighter()) || next.getValue().equals(c.darker())){
				result.add(next.getKey());
				return result;
			} else {
				Color value = next.getValue();
				int blue = c.getBlue() - value.getBlue();
				int red = c.getRed() - value.getRed();
				int green = c.getGreen() - value.getGreen();
				if (-COLORDT > blue || blue > COLORDT){
					continue;
				}
				if(-COLORDT > red || red > COLORDT){
					continue;
				}
				if (-COLORDT > green || green > COLORDT){
					continue;
				}
				Point key = next.getKey();
				result.add(key);
//				colorMap.remove(key);
			}
		}
		for (Point point : result) {
			colorMap.remove(point);
		}
		return result;
	}
	/**
	 * Removes listener
	 * 
	 * @param l
	 * @return
	 */
	public MainPicture removeListener(ImagePaintedListener l) {
		listeners.remove(l);
		return this;
	}

	/**
	 * This class is used to calculate average color each of little square on big picture
	 * @author akozlowski
	 *
	 */
	class ImageCountWorker extends SwingWorker<Boolean, Integer> {

		private BufferedImage bi;

		/**
		 * Constructor get BufferedImage as param
		 * @param bi
		 */
		public ImageCountWorker(BufferedImage bi) {
			this.bi = bi;			
		}

		@Override
		protected void done() {
			MainPicture.this.repaint();

		}
		
		@Override
		protected void process(List<Integer> chunks) {
		}
		

		@Override
		protected Boolean doInBackground() throws Exception {
			int h = bi.getHeight();
			int w = bi.getWidth();
			int wMini = (int) (w * lenFactor); // szerokosc miniaturowego zdjecia
			int hMini = h / (h / wMini);// wysokosc miniturowego zdjecia

			int x = 0;
			int y = 0;
			while (y < h && y+hMini < this.bi.getHeight()) {
				while (x < w && (x+wMini) < this.bi.getWidth()) {
					BufferedImage image = this.bi.getSubimage(x, y, wMini, hMini);
					Color color = Picture.getAverageColor(image);
					Graphics2D graphics = (Graphics2D) image.getGraphics();
					graphics.setColor(color);
					colorMap.put(new Point(x,y, wMini, hMini), color);
					graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
					x += wMini;
				}
				x=0;
				y+=hMini;
			}
			return true;
		}
	}

	@Override
	public void onImageInsertion() {
		this.repaint();
	}
}
