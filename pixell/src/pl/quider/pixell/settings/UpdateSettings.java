package pl.quider.pixell.settings;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UpdateSettings extends JPanel {

	/**
	 * Create the panel.
	 */
	public UpdateSettings() {
		
		JCheckBox chckbxWczAutomatyczneAktualizacje = new JCheckBox("W\u0142\u0105cz automatyczne aktualizacje");
		chckbxWczAutomatyczneAktualizacje.setSelected(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Automatyczne aktualizacje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblAktualnaWersja = new JLabel("Aktualna wersja : ");
		
		JLabel lblBeta = new JLabel("Beta");
		
		JButton btnZapisz = new JButton("Zapisz");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxWczAutomatyczneAktualizacje, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAktualnaWersja)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBeta))
						.addComponent(btnZapisz, Alignment.TRAILING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(chckbxWczAutomatyczneAktualizacje)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAktualnaWersja)
						.addComponent(lblBeta))
					.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
					.addComponent(btnZapisz)
					.addContainerGap())
		);
		
		JRadioButton rdbtnSprawdzajPrzyStarcie = new JRadioButton("Sprawdzaj przy starcie programu");
		rdbtnSprawdzajPrzyStarcie.setSelected(true);
		
		JRadioButton rdbtnSprawdzajPrzyZamykaniu = new JRadioButton("Sprawdzaj przy zamykaniu programu");
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

	}
}
