package pl.quider.pixell;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Pixell {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pixell window = new Pixell();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pixell() {
		try {
			initialize();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.add("Center", new Picture());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class Picture extends Component {
		private Graphics2D g2d;
		private BufferedImage bi;

		public Picture() throws IOException {
			System.out.println("Picture:");
			bi = ImageIO
					.read(new File("C:/Users/akozlowski/Pictures/nagklowek.png"));
		}
		@Override
		public void paint(Graphics g) {
			g2d = (Graphics2D) g;
			int index = 0;
			long redSum = 0;
			long greenSum = 0;
			long blueSum = 0;
			for (int x = bi.getMinX(); x < bi.getWidth(); x++) {
				for(int y=bi.getMinY(); y < bi.getHeight(); y++){
					int rgb = bi.getRGB(x, y);
					Color c = new Color(rgb);
					int red =  c.getRed();
					redSum += red;
					int green = c.getGreen();
					greenSum =+ green;
					int blue = c.getBlue();
					blueSum += blue;
					c = new Color(red, green, blue);
					bi.setRGB(x, y, c.getRGB());
					index++;
				}
			}
		
			g2d.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
			
			g2d.setColor(Color.WHITE);
//			for (int x = bi.getMinX(); x < bi.getWidth(); x++) {
//				if (x % 20 == 0) {
//					g2d.drawLine(x, bi.getMinY(), x, bi.getHeight());
//				}
//			}
//			for (int y = bi.getMinY(); y < bi.getHeight(); y++) {
//				if (y % 20 == 0) {
//					g2d.drawLine(bi.getMinX(), y, bi.getWidth(), y);
//				}
//			}
			
//			for (int x = bi.getMinX(); x < bi.getWidth(); x++) {
//				if(x%60 == 0)
			g2d.setColor(new Color(new Integer((int) (redSum/index)), (int)greenSum/index, (int)blueSum/index));
					g2d.fillRect(bi.getMinX(), bi.getMinY(), 60, 60);
//			}
		}

		public Graphics2D getG2d() {
			return g2d;
		}

		public void setG2d(Graphics2D g2d) {
			this.g2d = g2d;
		}
	}
}
