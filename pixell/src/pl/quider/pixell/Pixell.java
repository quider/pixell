package pl.quider.pixell;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import pl.quider.pixel.events.ImagePaintedListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pixell {

	private JFrame frame;
	private MainPicture mainPicture;

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
		
		mainPicture = new MainPicture("C:/Users/akozlowski/Pictures/Bez tytu³u.png");
		mainPicture.addListener(new ImagePaintedListener() {
			
			@Override
			public void onImagePainted(BufferedImage bi) {
				frame.setBounds(frame.getX(), frame.getY(), bi.getWidth()+20, bi.getHeight()+70);
				
			}
		});
		frame.getContentPane().add(mainPicture, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);
		
		JMenu mnObraz = new JMenu("Obraz");
		menuBar.add(mnObraz);
		
		JMenuItem mntmGwnyObraz = new JMenuItem("G\u0142\u00F3wny obraz");
		mntmGwnyObraz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				int showOpenDialog = fc.showOpenDialog(frame);
				if(showOpenDialog == JFileChooser.APPROVE_OPTION){
					try {
						mainPicture.readInPicture(fc.getSelectedFile().getPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		mnObraz.add(mntmGwnyObraz);
		
		JMenuItem mntmKatalogObrazkw = new JMenuItem("Katalog obrazk\u00F3w");
		mnObraz.add(mntmKatalogObrazkw);
	}

	
}
