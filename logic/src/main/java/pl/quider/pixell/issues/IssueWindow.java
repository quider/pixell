package pl.quider.pixell.issues;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import pl.quider.pixell.github.Issue;

public class IssueWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5686326362480020810L;
	private JPanel contentPane;
	private JTextField textTitle;
	private JTextPane textbody;
	private JLabel lblOpen;
	private JLabel labeldate;
	private JLabel lblnumber;
	private JLabel lblURL;
	private JButton btnCancel;
	private JButton btnSend;

	/**
	 * Create the frame.
	 */
	public IssueWindow() {
		setResizable(false);
		setTitle("Zgłoś błąd");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblTytu = new JLabel("Tytuł:");

		textTitle = new JTextField();
		textTitle.setColumns(10);

		JLabel lblTreZgoszenia = new JLabel("Treść zgłoszenia:");

		textbody = new JTextPane();

		JLabel lblZgoszoneDnia = new JLabel("Zgłoszone dnia:");

		labeldate = new JLabel("00-00-0000");

		JLabel lblNumerZgoszenia = new JLabel("Numer zgłoszenia:");

		lblnumber = new JLabel("####");

		JLabel lblStatus = new JLabel("Status:");

		lblOpen = new JLabel("");

		btnSend = new JButton("Wyślij Zgłoszenie");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Issue, Boolean> issueWorker = new SwingWorker<Issue, Boolean>() {

					@Override
					protected void done() {
						try {
							Issue issue = get();
							textTitle.setText(issue.getTitle());
							textTitle.setEditable(false);
							textbody.setText(issue.getBody());
							textbody.setEditable(false);
							textbody.setEnabled(false);
							lblnumber.setText(Integer.toString(issue.getNumber()));
							lblOpen.setText(issue.getState());
							labeldate.setText(issue.getCreated_at());
							lblURL.setText(issue.getHtml_url());
							btnSend.setEnabled(false);
							btnCancel.setText("Zamknij okno");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					@Override
					protected Issue doInBackground() throws Exception {
						APIHandler apiHandler = new APIHandler();
						Issue issue = new Issue();
						issue.setTitle(textTitle.getText());
						issue.setBody(textbody.getText());
						return apiHandler.sendIssue(issue);
					}
				};
				issueWorker.execute();
			}
		});

		btnCancel = new JButton("Olej sprawę i zamknij okno");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IssueWindow.this.dispose();
			}
		});
		
		JLabel lblAdresUrlZgoszenia = new JLabel("Adres url zgłoszenia:");
		
		lblURL = new JLabel("");
		lblURL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 if (Desktop.isDesktopSupported()) {
		                Desktop desktop = Desktop.getDesktop();
		                
		                try {
		                    URI uri = new URI(lblURL.getText());
		                    desktop.browse(uri);
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                } catch (URISyntaxException ex) {
		                    ex.printStackTrace();
		                }
		        }
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(textTitle, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textbody, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNumerZgoszenia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblOpen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblnumber, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labeldate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblZgoszoneDnia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(57))))
						.addComponent(lblTytu)
						.addComponent(lblTreZgoszenia)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAdresUrlZgoszenia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblURL, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblTytu)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblTreZgoszenia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textbody, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblZgoszoneDnia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labeldate)
							.addGap(17)
							.addComponent(lblNumerZgoszenia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblnumber)
							.addGap(18)
							.addComponent(lblStatus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOpen)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdresUrlZgoszenia)
						.addComponent(lblURL))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSend)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}
