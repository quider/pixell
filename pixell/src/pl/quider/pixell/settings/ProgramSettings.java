package pl.quider.pixell.settings;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class ProgramSettings extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6736150645309544320L;
	private JTextField textDefaultDirectory;

	/**
	 * Create the panel.
	 */
	public ProgramSettings() {
		
		JLabel lblDomylnyKatalogWyszukiwania = new JLabel("Domy\u015Blny katalog wyszukiwania obrazk\u00F3w");
		
		textDefaultDirectory = new JTextField();
		textDefaultDirectory.setEditable(false);
		textDefaultDirectory.setColumns(10);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String property = System.getProperty(SettingsUtils.PROGRAM_DIRECTORY);
				File direc = null;
				if(property != null){
					direc = new File(property);
				}
				JFileChooser fc = new JFileChooser(direc);
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int i = fc.showOpenDialog(null);
				if (i == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					textDefaultDirectory.setText(selectedFile.getAbsolutePath());
					System.setProperty(SettingsUtils.PROGRAM_DIRECTORY, textDefaultDirectory.getText());
				}
			}
		});
		button.setIcon(new ImageIcon(ProgramSettings.class.getResource("/resources/directory_accept.png")));
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.setProperty(SettingsUtils.PROGRAM_DIRECTORY, textDefaultDirectory.getText());
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textDefaultDirectory, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
						.addComponent(lblDomylnyKatalogWyszukiwania)
						.addComponent(btnZapisz, Alignment.TRAILING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDomylnyKatalogWyszukiwania)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textDefaultDirectory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
					.addComponent(btnZapisz)
					.addContainerGap())
		);
		setLayout(groupLayout);
		initialize();
	}
	
	private void initialize(){
		String programDirectory = System.getProperty(SettingsUtils.PROGRAM_DIRECTORY);
		textDefaultDirectory.setText(programDirectory);
		
	}

}
