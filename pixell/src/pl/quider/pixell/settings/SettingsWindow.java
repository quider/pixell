package pl.quider.pixell.settings;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class SettingsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -227402118063922315L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsWindow frame = new SettingsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SettingsWindow() {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Ustawienia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		ImageSettings imageSettings = new ImageSettings();
		tabbedPane.addTab("Ustawienia mozaiki", null, imageSettings, null);
		
		ProgramSettings programSettings = new ProgramSettings();
		tabbedPane.addTab("Ustawienia prgoramu", null, programSettings, null);
		
		UpdateSettings updateSettings = new UpdateSettings();
		tabbedPane.addTab("Ustawienia aktualizacji", null, updateSettings, null);
	}

}
