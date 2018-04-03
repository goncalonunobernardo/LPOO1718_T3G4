package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsBtn extends JPanel implements ActionListener {

	private JButton sett_btn;
	private Settings settingsFrame;
	
	SettingsBtn (Settings settingsFrame) {
		this.settingsFrame = settingsFrame;
		
		sett_btn = new JButton ("Settings");
		sett_btn.addActionListener(this);
		
		add(sett_btn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		settingsFrame.setVisible(true);
	}
}
