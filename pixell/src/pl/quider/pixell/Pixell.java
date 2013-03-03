package pl.quider.pixell;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Pixell {

	private JFrame frame;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
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

	public Pixell() throws IOException {
		this.frame = new JFrame();
		frame.setSize(new Dimension(640, 480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Linia 1"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(list, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(list, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.EAST);
		
		PicturePanel picturePanel = new PicturePanel();
		frame.getContentPane().add(picturePanel, BorderLayout.CENTER);

		File file = new File("D:/Pictures/Babia góra/20120519_141245.jpg");
		ImageInputStream is = ImageIO.createImageInputStream(file);
		Iterator iter = ImageIO.getImageReaders(is);

		if (!iter.hasNext()) {
			System.out.println("Cannot load the specified file " + file);
			System.exit(1);
		}
		ImageReader imageReader = (ImageReader) iter.next();
		imageReader.setInput(is);

		BufferedImage image = imageReader.read(0);
		picturePanel.loadPicture(image);

		int height = image.getHeight();
		int width = image.getWidth();

		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int[] rgbArr = getRGBArr(rgb);
				Integer counter = m.get(rgb);
				if (counter == null)
					counter = 0;
				counter = 0;
				counter++;
				m.put(rgb, counter);
			}
		}
		String colourHex = getMostCommonColour(m);
		System.out.println(colourHex);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getMostCommonColour(Map<Integer, Integer> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		Map.Entry me = (Map.Entry) list.get(list.size() - 1);
		int[] rgb = getRGBArr((Integer) me.getKey());
		return "#" + Integer.toHexString(rgb[0]) + ""
				+ Integer.toHexString(rgb[1]) + ""
				+ Integer.toHexString(rgb[2]);
	}

	public static int[] getRGBArr(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return new int[] { red, green, blue };

	}

	public static boolean isGray(int[] rgbArr) {
		int rgDiff = rgbArr[0] - rgbArr[1];
		int rbDiff = rgbArr[0] - rgbArr[2];
		// Filter out black, white and grays...... (tolerance within 10 pixels)
		int tolerance = 10;
		if (rgDiff > tolerance || rgDiff < -tolerance)
			if (rbDiff > tolerance || rbDiff < -tolerance) {
				return false;
			}
		return true;
	}
}