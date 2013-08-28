package pl.quider.pixell.settings;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import pl.quider.pixell.Register;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImageSettings extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3064371471654125308L;
	private JTextField texImageFactor;
	private JTextField textColorDT;

	/**
	 * Create the panel.
	 */
	public ImageSettings() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Stosunek boku", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Przeskok koloru", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.getInstance().setProperty(SettingsUtils.IMAGE_COLOR_DT, textColorDT.getText());
				Register.getInstance().setProperty(SettingsUtils.IMAGE_FACTOR, texImageFactor.getText());
				Register.getInstance().save();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 435, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
						.addComponent(btnZapisz, Alignment.TRAILING))
					.addGap(5))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
					.addComponent(btnZapisz)
					.addContainerGap())
		);
		
		textColorDT = new JTextField();
		textColorDT.setText("3");
		textColorDT.setColumns(10);
		
		JTextPane txtpnPrzeskokKoloruJest = new JTextPane();
		txtpnPrzeskokKoloruJest.setText("Przeskok koloru jest liczb\u0105 barw jak\u0105 mo\u017Cna przyr\u00F3wna\u0107 do jednego koloru w palecie RGB. Domy\u015Blnie jest to 3 dla ka\u017Cdego koloru RGB, co daje 27 barw.");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(textColorDT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnPrzeskokKoloruJest, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(txtpnPrzeskokKoloruJest, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textColorDT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		texImageFactor = new JTextField();
		texImageFactor.setText("0.025");
		texImageFactor.setColumns(10);
		
		JTextPane txtpnStosunekBokuOznacza = new JTextPane();
		txtpnStosunekBokuOznacza.setText("Oznacza ile procent ca\u0142ej d\u0142ugo\u015Bci kraw\u0119dzi du\u017Cego zdj\u0119cia powinno zajmowa\u0107 jedno zdj\u0119cie w mozaice. Domy\u015Blnie ok 2,5%");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(texImageFactor, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(txtpnStosunekBokuOznacza, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnStosunekBokuOznacza, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(texImageFactor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		initialize();
	}
	
	private void initialize(){
		String imageFactor = Register.getInstance().getProperty(SettingsUtils.IMAGE_FACTOR, "0.025");
		String imageColorDT = Register.getInstance().getProperty(SettingsUtils.IMAGE_COLOR_DT, "3");
		
		texImageFactor.setText(imageFactor);
		textColorDT.setText(imageColorDT);
	}
}
