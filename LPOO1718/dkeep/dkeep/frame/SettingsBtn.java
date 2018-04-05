package dkeep.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SettingsBtn extends JButton implements ActionListener {

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
