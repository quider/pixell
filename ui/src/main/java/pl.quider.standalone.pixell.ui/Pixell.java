package pl.quider.standalone.pixell.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.quider.pixell.events.ImagePaintedListener;
//import pl.quider.pixell.issues.IssueWindow;
import pl.quider.pixell.settings.SettingsUtils;
import pl.quider.pixell.settings.SettingsWindow;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class Pixell {
	public static final String VERSION = "beta 1.1";

	private JFrame frmPixellMosaic;
	private MainPicture mainPicture;
	public Map<String, Color> map = new HashMap<String, Color>();
	private JTextField txtFilesCount;

	static {
		Boolean autoenabled = new Boolean(Register.getInstance().getProperty(
				SettingsUtils.UPDATE_AUTOENABLED, null));
		if (autoenabled) {
			Boolean updateAtStart = new Boolean(Register.getInstance()
					.getProperty(SettingsUtils.UPDATE_AT_START, null));
			if (updateAtStart) {
				// TODO: Run update in new thread
			}
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
    } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
		
		String property = Register.getInstance().getProperty(
				SettingsUtils.VERSION, "Beta");
		if (!property.equals("Beta 1.1")) {
			System.setProperty(SettingsUtils.VERSION, VERSION);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pixell window = new Pixell();
					window.frmPixellMosaic.setVisible(true);
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
		frmPixellMosaic = new JFrame();
		frmPixellMosaic.setTitle("Pixell - mosaic maker Beta 1.3");
		frmPixellMosaic.setBounds(100, 100, 538, 352);

		mainPicture = new MainPicture("");
		mainPicture.addListener(new ImagePaintedListener() {

			@Override
			public void onImagePainted(Image bi) {
				frmPixellMosaic.setBounds(frmPixellMosaic.getX(),
						frmPixellMosaic.getY(), bi.getWidth(null) + 20,
						bi.getHeight(null) + 70);

			}
		});
		frmPixellMosaic.getContentPane().add(mainPicture, BorderLayout.CENTER);
		
		JProgressBar progressBar = new JProgressBar();
		frmPixellMosaic.getContentPane().add(progressBar, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmPixellMosaic.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("39px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("24px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblObrazki = new JLabel("Ilo\u015B\u0107 obrazk\u00F3w:");
		panel.add(lblObrazki, "2, 2, 3, 1");
		
		txtFilesCount = new JTextField();
		txtFilesCount.setEditable(false);
		panel.add(txtFilesCount, "2, 4, 3, 1, fill, center");
		txtFilesCount.setColumns(10);
		
		JButton btnMosaic = new JButton("Rozpocznij!");
		panel.add(btnMosaic, "2, 20, 3, 1");
		frmPixellMosaic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmPixellMosaic.setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);

		JMenuItem menuSettings = new JMenuItem("Ustawienia");
		menuSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingsWindow().setVisible(true);
			}
		});
		menuSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		menuSettings.setIcon(new ImageIcon(Pixell.class
				.getResource("/resources/advancedsettings.png")));
		mnProgram.add(menuSettings);

		JMenuItem menuCheckActualization = new JMenuItem(
				"Sprawd\u017A aktualizacj\u0119");
		menuCheckActualization.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, InputEvent.CTRL_MASK));
		menuCheckActualization.setIcon(new ImageIcon(Pixell.class
				.getResource("/resources/box_download.png")));
		mnProgram.add(menuCheckActualization);
		
		JMenuItem mntmZgoBd = new JMenuItem("Zg�o� b��d");
		mntmZgoBd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				new IssueWindow().setVisible(true);
			}
		});
		mnProgram.add(mntmZgoBd);

		JSeparator separator = new JSeparator();
		mnProgram.add(separator);

		JMenuItem menuExit = new JMenuItem("Wyjd\u017A");
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		menuExit.setIcon(new ImageIcon(Pixell.class
				.getResource("/resources/exit.png")));
		mnProgram.add(menuExit);

		JMenu mnObraz = new JMenu("Obraz");
		menuBar.add(mnObraz);

		JMenuItem mntmGwnyObraz = new JMenuItem("G\u0142\u00F3wny obraz");
		mntmGwnyObraz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mntmGwnyObraz.setIcon(new ImageIcon(Pixell.class
				.getResource("/resources/pics_2.png")));
		mntmGwnyObraz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				int showOpenDialog = fc.showOpenDialog(frmPixellMosaic);
				if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
					try {
						mainPicture.readInPicture(fc.getSelectedFile()
								.getPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		mnObraz.add(mntmGwnyObraz);

		JMenuItem mntmKatalogObrazkw = new JMenuItem("Katalog obrazk\u00F3w");
		mntmKatalogObrazkw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mntmKatalogObrazkw.setIcon(new ImageIcon(Pixell.class
				.getResource("/resources/directory_accept.png")));
		mntmKatalogObrazkw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int i = fc.showOpenDialog(Pixell.this.frmPixellMosaic);
				if (i == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					
					Runnable sniffer = new CatalogSniffer(Pixell.this,
							selectedFile, Pixell.this.txtFilesCount);
					Thread executor = new Thread(sniffer);
					executor.start();
				}
			}
		});
		mnObraz.add(mntmKatalogObrazkw);

		JSeparator separator_1 = new JSeparator();
		mnObraz.add(separator_1);

		JMenuItem menuSaveMosaic = new JMenuItem("Zapisz obraz");
		menuSaveMosaic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BufferedImage bi = Pixell.this.mainPicture.getBi();
					File outputfile = new File("mainImage.jpg");
					ImageIO.write(bi, "jpg", outputfile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuSaveMosaic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		menuSaveMosaic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		menuSaveMosaic.setIcon(new ImageIcon(Pixell.class
				.getResource("/resources/3_disc.png")));
		mnObraz.add(menuSaveMosaic);

		JSeparator separator_2 = new JSeparator();
		mnObraz.add(separator_2);

		JMenuItem menuClearMainImage = new JMenuItem(
				"Wyczy\u015B\u0107 obraz g\u0142\u00F3wny");
		menuClearMainImage.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_DELETE, InputEvent.SHIFT_MASK));
		menuClearMainImage.setIcon(new ImageIcon(Pixell.class
				.getResource("/resources/recycle_bin_f.png")));
		mnObraz.add(menuClearMainImage);
	}

	public synchronized void addPictureToMap(Color c, String path) {
		System.out.println(path + ": " + c.toString());
		map.put(path, c);
		List<Point> point = mainPicture.findColorOnMosaic(c);
		if (point != null) {
			System.err.println("Pasuj�cy kolor! ");
			try {
				ReplaceColorWithImage replaceColorWithImage = new ReplaceColorWithImage(
						point, mainPicture.getBi(),
						ImageIO.read(new File(path)));
				replaceColorWithImage.addListener(mainPicture);
				Thread t = new Thread(replaceColorWithImage);
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
