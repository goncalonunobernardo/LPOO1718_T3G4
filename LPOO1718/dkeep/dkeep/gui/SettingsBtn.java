package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsBtn extends JButton implements ActionListener {

	private JButton sett_btn;
	private SettingsFrame settingsFrame;
	
	SettingsBtn (SettingsFrame settingsFrame) {
		super();
		
		this.settingsFrame = settingsFrame;
		
		this.setText("Settings");
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		settingsFrame.setVisible(true);
	}
}
