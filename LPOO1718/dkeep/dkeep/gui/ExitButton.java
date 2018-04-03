package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ExitButton extends JPanel implements ActionListener {
	
	JButton button;
	
	ExitButton() {
		button = new JButton("Exit");
		button.setBounds(614, 460, 97, 25);
		button.addActionListener(this);
		
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0); 
	}
}
