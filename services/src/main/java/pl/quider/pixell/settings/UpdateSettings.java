package pl.quider.pixell.settings;

import pl.quider.pixell.Register;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSettings extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox cbEnableAutoUpdate;
	private JCheckBox rdbtnSprawdzajPrzyStarcie;
	private JCheckBox rdbtnSprawdzajPrzyZamykaniu;
	private JPanel panel;
	private JLabel lblVerson;

	/**
	 * Create the panel.
	 */
	public UpdateSettings() {
		
		cbEnableAutoUpdate = new JCheckBox("W\u0142\u0105cz automatyczne aktualizacje");
		cbEnableAutoUpdate.setSelected(true);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Automatyczne aktualizacje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblAktualnaWersja = new JLabel("Aktualna wersja : ");
		
		lblVerson = new JLabel("Beta");
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Register.getInstance().setProperty(SettingsConstants.UPDATE_AUTOENABLED, new Boolean(cbEnableAutoUpdate.isSelected()).toString());
                Register.getInstance().setProperty(SettingsConstants.UPDATE_AT_START, new Boolean(rdbtnSprawdzajPrzyStarcie.isSelected()).toString());
                Register.getInstance().setProperty(SettingsConstants.UPDATE_AT_END, new Boolean(rdbtnSprawdzajPrzyZamykaniu.isSelected()).toString());
                Register.getInstance().save();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cbEnableAutoUpdate, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAktualnaWersja)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVerson))
						.addComponent(btnZapisz, Alignment.TRAILING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(cbEnableAutoUpdate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAktualnaWersja)
						.addComponent(lblVerson))
					.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
					.addComponent(btnZapisz)
					.addContainerGap())
		);
		
		rdbtnSprawdzajPrzyStarcie = new JCheckBox("Sprawdzaj przy starcie programu");
		rdbtnSprawdzajPrzyStarcie.setSelected(true);
		
		rdbtnSprawdzajPrzyZamykaniu = new JCheckBox("Sprawdzaj przy zamykaniu programu");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(rdbtnSprawdzajPrzyZamykaniu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
						.addComponent(rdbtnSprawdzajPrzyStarcie, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnSprawdzajPrzyStarcie)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnSprawdzajPrzyZamykaniu)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

		initialize();
	}
	
	private void initialize(){
        String autoupdate = Register.getInstance().getProperty(SettingsConstants.UPDATE_AUTOENABLED, "true");
        String startupdate = Register.getInstance().getProperty(SettingsConstants.UPDATE_AT_START, "true");
        String stopupdate = Register.getInstance().getProperty(SettingsConstants.UPDATE_AT_END, "false");
        this.cbEnableAutoUpdate.setSelected(new Boolean(autoupdate));
		this.rdbtnSprawdzajPrzyStarcie.setSelected(new Boolean(startupdate));
		this.rdbtnSprawdzajPrzyZamykaniu.setSelected(new Boolean(stopupdate));
        this.lblVerson.setText(Register.getInstance().getProperty(SettingsConstants.VERSION, "beta"));
    }
}
