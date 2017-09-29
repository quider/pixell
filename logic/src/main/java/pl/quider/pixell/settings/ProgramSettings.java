package pl.quider.pixell.settings;

import pl.quider.pixell.Register;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                String property = Register.getInstance().getProperty(SettingsConstants.PROGRAM_DIRECTORY, null);
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
					//
                    Register.getInstance().setProperty(SettingsConstants.PROGRAM_DIRECTORY, textDefaultDirectory.getText());
                }
			}
		});
		button.setIcon(new ImageIcon(ProgramSettings.class.getResource("/resources/directory_accept.png")));
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Register.getInstance().setProperty(SettingsConstants.PROGRAM_DIRECTORY, textDefaultDirectory.getText());
                Register.getInstance().save();
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

        String programDirectory = Register.getInstance().getProperty(SettingsConstants.PROGRAM_DIRECTORY, null);
        textDefaultDirectory.setText(programDirectory);
		
	}

}
